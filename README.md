````markdown
# maestros-service (microservicio)

## Requisitos previos
- Docker (para levantar contenedores con docker compose)
- Java 17 (JDK)
- Maven (o usar el Maven Wrapper incluido `mvnw` / `mvnw.cmd`)

## Cómo ejecutar
1. Compilar el servicio (desde la carpeta `maestros-service`):

   ```powershell
   cd maestros-service
   .\mvnw.cmd clean package -DskipTests
   ```

2. Levantar PostgreSQL y el servicio con Docker Compose (desde la raíz del repo):

   ```powershell
   docker compose up --build
   ```

3. El servicio estará disponible en http://localhost:8081

## Comandos rápidos

- Levantar todo:
  - Linux/macOS: `./scripts/up.sh`
  - Windows (PowerShell): `mvn -q -f maestros-service/pom.xml clean package -DskipTests; docker compose up --build`

- Bajar y limpiar volúmenes:
  - Linux/macOS: `./scripts/down.sh`
  - Windows (PowerShell): `docker compose down -v`

- Probar:
  - Abrir `requests.http` y ejecutar cada request.

## Endpoints disponibles
- GET /api/maestros/clientes
- GET /api/maestros/clientes/{publicId}
- POST /api/maestros/clientes
- PUT /api/maestros/clientes/{publicId}
- DELETE /api/maestros/clientes/{publicId}

- GET /api/maestros/contenedores
- GET /api/maestros/contenedores/{publicId}
- POST /api/maestros/contenedores
- PUT /api/maestros/contenedores/{publicId}
- DELETE /api/maestros/contenedores/{publicId}

- GET /api/maestros/camiones
- GET /api/maestros/camiones/{publicId}
- POST /api/maestros/camiones
- PUT /api/maestros/camiones/{publicId}
- DELETE /api/maestros/camiones/{publicId}

## Estructura del proyecto
```
TPI-Backend/
  maestros-service/
    pom.xml
    Dockerfile
    src/main/java/com/tpi/maestros/...
    src/main/resources/application.yml
  api-gateway/
  operaciones-service/
  Docker-compose.yml
```

## operaciones-service

- **Puerto:** 8082  
- **Base de datos:** `operaciones_db` (contenedor `postgres_ops`, puerto 5433 externo)  
- **Endpoints base:** `/api/operaciones/*`  
- **Health:** `GET http://localhost:8082/health`

### Cómo ejecutar (junto con maestros-service)
- Requisitos: Docker Desktop, Java 17, Maven
- Linux/macOS:
  ```bash
  ./scripts/up.sh
  ```
# maestros-service (microservicio)

## Requisitos previos
- Docker (para levantar contenedores con docker compose)
- Java 17 (JDK)
- Maven (o usar el Maven Wrapper incluido `mvnw` / `mvnw.cmd`)

## Cómo ejecutar
1. Compilar el servicio (desde la carpeta `maestros-service`):

   ```powershell
   cd maestros-service
   .\mvnw.cmd clean package -DskipTests
   ```

2. Levantar PostgreSQL y el servicio con Docker Compose (desde la raíz del repo):

   ```powershell
   docker compose up --build
   ```

3. El servicio estará disponible en http://localhost:8081

## Comandos rápidos

- Levantar todo:
  - Linux/macOS: `./scripts/up.sh`
  - Windows (PowerShell): `mvn -q -f maestros-service/pom.xml clean package -DskipTests; docker compose up --build`

- Bajar y limpiar volúmenes:
  - Linux/macOS: `./scripts/down.sh`
  - Windows (PowerShell): `docker compose down -v`

- Probar:
  - Abrir `requests.http` y ejecutar cada request.

## Endpoints disponibles
- GET /api/maestros/clientes
- GET /api/maestros/clientes/{publicId}
- POST /api/maestros/clientes
- PUT /api/maestros/clientes/{publicId}
- DELETE /api/maestros/clientes/{publicId}

- GET /api/maestros/contenedores
- GET /api/maestros/contenedores/{publicId}
- POST /api/maestros/contenedores
- PUT /api/maestros/contenedores/{publicId}
- DELETE /api/maestros/contenedores/{publicId}

- GET /api/maestros/camiones
- GET /api/maestros/camiones/{publicId}
- POST /api/maestros/camiones
- PUT /api/maestros/camiones/{publicId}
- DELETE /api/maestros/camiones/{publicId}

## Estructura del proyecto
```
TPI-Backend/
  maestros-service/
    pom.xml
    Dockerfile
    src/main/java/com/tpi/maestros/...
    src/main/resources/application.yml
  api-gateway/
  operaciones-service/
  Docker-compose.yml
```
