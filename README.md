# Proyecto de microservicios : microservicios para inventario de una tienda historietas.

> En este repositorio se guarda los archivos de una api desarrollada con Springboot y Java , esta api pertenece a un sistema de micro servicios del siguiente repositorio : https://github.com/gabriel1-du/Optativo-Desarollo-Java-Springboot-Cloud

## Tecnologías Utilizadas

* **Lenguaje:** Java
* **Frameworks / Librerías:** Springboot
* **Base de datos:** MySQL 
* **Otras herramientas:** Git

---

## Diagrama de la base de datos 

![MER](<Base de datos/MER.png>)
---
## Arquitectura y Flujo de Datos

El proyecto implementa una arquitectura en capas basada en separación de responsabilidades:

```
```text
[ Cliente / Frontend ]
         │ ▲
(HTTP)   ▼ │ (JSON / DTO)
   ┌─────────────┐
   │ Controller  │ ──► Expone los endpoints REST y gestiona la entrada/salida HTTP
   └─────────────┘
         │ ▲
(DTOs)   ▼ │
   ┌─────────────┐
   │   Service   │ ──► Interfaz: define el contrato de la lógica de negocio
   └─────────────┘
         │ ▲
         ▼ │
   ┌─────────────┐
   │ ServiceImpl │ ──► Implementación: ejecuta reglas de negocio, validaciones y mapeo DTO ◄─► Modelo
   └─────────────┘
         │ ▲
(Model)  ▼ │
   ┌─────────────┐
   │ Repository  │ ──► Interfaz Spring Data JPA: acceso y operaciones sobre la base de datos
   └─────────────┘
         │ ▲
(SQL)    ▼ │
   ┌─────────────┐
   │   Database  │
   └─────────────┘

* DTO: Objeto transversal utilizado para transferir datos limpios entre capas sin exponer el Modelo o facilitar el cuerpo en las peticiones.

```
---
# Dependencias (pom)

* Spring Boot Starter Data JPA
* Spring Boot Starter RestClient
* Spring Boot Starter WebMVC
* MySQL Connector/J
* Project Lombok
* Spring Boot Starter Data JPA Test
* Spring Boot Starter RestClient Test
* Spring Boot Starter WebMVC Test