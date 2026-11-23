#!/usr/bin/env bash
# Muestra quién está usando el puerto MySQL del host.
# Uso: ./scripts/check-db-port.sh [puerto]   (por defecto 3306)
PORT="${1:-3306}"

echo "== Procesos escuchando en :$PORT =="
if command -v ss >/dev/null 2>&1; then
    sudo ss -ltnp "sport = :$PORT" || true
elif command -v lsof >/dev/null 2>&1; then
    sudo lsof -nP -iTCP:"$PORT" -sTCP:LISTEN || true
else
    echo "Ni ss ni lsof están disponibles."
fi

echo
echo "== Contenedores Docker que publican :$PORT =="
sudo docker ps --filter "publish=$PORT" \
    --format 'table {{.Names}}\t{{.Image}}\t{{.Ports}}'

echo
echo "Si el contenedor que aparece es de otro proyecto, podés:"
echo "  - Pararlo:        sudo docker stop <nombre>"
echo "  - O dejar este proyecto en 3307 (DB_PORT=3307 en .env)."
echo "Cuando el 3306 esté libre, poné DB_PORT=3306 en .env y:"
echo "  sudo docker compose down && sudo docker compose up -d --build"
