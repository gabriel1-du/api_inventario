#!/usr/bin/env bash
set -euo pipefail

# ============================================================
# deploy.sh - Despliegue de api_inventario en EC2 con docker compose
#
# Se ejecuta desde cualquier lado; siempre trabaja en la raíz del proyecto.
#
# Uso:
#   ./deploy.sh [rama]            Despliega de verdad
#   ./deploy.sh [rama] --check    Solo valida entorno/.env/compose (no modifica nada)
#   ./deploy.sh [rama] --dry-run  Muestra lo que haría, sin git pull ni docker up
#   ./deploy.sh --help
# ============================================================

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# El script puede estar en la raíz o dentro de otros/.
# Si el compose está junto al script, esa es la raíz; si no, subimos un nivel.
if [[ -f "${SCRIPT_DIR}/docker-compose.prod.yml" ]]; then
  PROJECT_ROOT="${SCRIPT_DIR}"
else
  PROJECT_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"
fi
cd "${PROJECT_ROOT}"

# Inicialización de variables
RAMA="main"
MODO="normal"
COMPOSE_FILE="docker-compose.prod.yml"
ENV_FILE=".env.prod"
SERVICIO="api-inventario"
HEALTH_PATH="/api/generoHistApi/"

# Comando Docker Compose reutilizable (siempre con el archivo y el env correctos)
DC=(sudo docker compose -f "${COMPOSE_FILE}" --env-file "${ENV_FILE}")

usage() {
  cat <<'EOF'
Uso:
  ./deploy.sh [rama]            Despliega de verdad
  ./deploy.sh [rama] --check    Solo valida entorno/.env/compose (no modifica nada)
  ./deploy.sh [rama] --dry-run  Muestra lo que haría, sin git pull ni docker up
  ./deploy.sh --help
EOF
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --check|--check-only) MODO="check"; shift ;;
    --dry-run)            MODO="dry-run"; shift ;;
    -h|--help)            usage; exit 0 ;;
    -*)                   echo "[deploy] Opción desconocida: $1"; usage; exit 1 ;;
    *)                    RAMA="$1"; shift ;;
  esac
done

log() { printf '[deploy] %s\n' "$*"; }

# Ejecuta el comando, o solo lo muestra en modo check/dry-run
run() {
  if [[ "$MODO" == "check" || "$MODO" == "dry-run" ]]; then
    printf '[simulado]'; printf ' %q' "$@"; printf '\n'
  else
    "$@"
  fi
}

log "Modo: ${MODO} | rama: ${RAMA} | compose: ${COMPOSE_FILE} | env: ${ENV_FILE} | root: ${PROJECT_ROOT}"

# --- 1. HERRAMIENTAS ---
for CMD in git docker; do
  if ! command -v "${CMD}" >/dev/null 2>&1; then
    log "ERROR: no encontré '${CMD}' en el PATH"
    exit 1
  fi
done

if ! docker compose version >/dev/null 2>&1; then
  log "ERROR: 'docker compose' no está disponible (usa docker compose v2)"
  exit 1
fi

# --- 2. ARCHIVOS Y VARIABLES ---
if [[ ! -f "${COMPOSE_FILE}" ]]; then
  log "ERROR: falta ${COMPOSE_FILE} en ${PROJECT_ROOT}"
  exit 1
fi

if [[ ! -f "${ENV_FILE}" ]]; then
  log "ERROR: falta ${ENV_FILE} en ${PROJECT_ROOT} (copia .env.prod.example y complétalo)"
  exit 1
fi

# Variables obligatorias para RDS + S3 real
for VAR in DB_HOST DB_PORT DB_NAME DB_USER DB_PASSWORD SERVER_PORT AWS_REGION AWS_S3_BUCKET_NAME; do
  if ! grep -Eq "^${VAR}=.+" "${ENV_FILE}"; then
    log "ERROR: ${ENV_FILE} no tiene un valor para ${VAR}"
    exit 1
  fi
done

if grep -Eq '^DB_HOST=(localhost|127\.0\.0\.1)' "${ENV_FILE}"; then
  log "ADVERTENCIA: DB_HOST apunta a localhost; en EC2 debe ser el endpoint de RDS"
fi

if grep -Eq '^AWS_S3_ENDPOINT=http://localhost' "${ENV_FILE}"; then
  log "ADVERTENCIA: AWS_S3_ENDPOINT apunta a SeaweedFS local; en producción debe estar vacío"
fi

# Validación real del compose (interpola .env.prod y comprueba la sintaxis)
if ! docker compose -f "${COMPOSE_FILE}" --env-file "${ENV_FILE}" config -q; then
  log "ERROR: ${COMPOSE_FILE} no es válido con ${ENV_FILE}"
  exit 1
fi
log "Compose válido ✅"

# --- 3. GIT ---
if ! git rev-parse --is-inside-work-tree >/dev/null 2>&1; then
  log "ERROR: este directorio no es un repositorio git"
  exit 1
fi

if ! git rev-parse --verify --quiet "refs/heads/${RAMA}" >/dev/null; then
  log "La rama local '${RAMA}' no existe; se creará desde origin/${RAMA}"
  run git fetch origin "${RAMA}"
  run git checkout -b "${RAMA}" "origin/${RAMA}"
else
  run git fetch origin
  run git checkout "${RAMA}"
fi
run git pull origin "${RAMA}"

# --- 4. CONSTRUCCIÓN Y ARRANQUE ---
run "${DC[@]}" up -d --build
run sudo docker image prune -f

# --- 5. COMPROBACIÓN (solo en modo real) ---
if [[ "$MODO" == "normal" ]]; then
  PUERTO=$(grep -E '^HOST_PORT=' "${ENV_FILE}" | head -1 | cut -d= -f2)
  PUERTO="${PUERTO:-$(grep -E '^SERVER_PORT=' "${ENV_FILE}" | head -1 | cut -d= -f2)}"
  PUERTO="${PUERTO:-8083}"

  if command -v curl >/dev/null 2>&1; then
    log "Esperando a que la API responda en http://localhost:${PUERTO}${HEALTH_PATH} ..."
    OK=0
    for _ in $(seq 1 30); do
      if curl -fsS "http://localhost:${PUERTO}${HEALTH_PATH}" >/dev/null 2>&1; then
        OK=1
        break
      fi
      sleep 2
    done
    if [[ "$OK" -eq 1 ]]; then
      log "Healthcheck OK ✅"
    else
      log "ERROR: la API no respondió. Revisa: ${DC[*]} logs ${SERVICIO}"
      exit 1
    fi
  else
    log "ADVERTENCIA: curl no está instalado; se omite el healthcheck"
  fi

  log "Últimas líneas de Spring Boot:"
  "${DC[@]}" logs --tail=50 "${SERVICIO}"
  "${DC[@]}" ps

  log "Comandos útiles:"
  log "  Seguir logs:  ${DC[*]} logs -f ${SERVICIO}"
  log "  Estado:       ${DC[*]} ps"
  log "  Reiniciar:    ${DC[*]} restart ${SERVICIO}"
fi

log "Despliegue finalizado (modo: ${MODO})"
