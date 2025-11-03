# Etapa de Build (Construcción)
# Usamos una imagen de Maven con Java 21 para construir el JAR
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar solo el pom.xml para descargar dependencias (esto optimiza el cache de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el resto del código fuente y construir el proyecto
COPY src ./src
RUN mvn package -DskipTests

# Etapa de Run (Ejecución)
# Usamos una imagen ligera solo con Java 21 JRE
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copiar el JAR construido desde la etapa 'build'
# El nombre debe coincidir con el <artifactId> de tu api-gateway/pom.xml
COPY --from=build /app/target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 9000 que definimos en el application.yml
EXPOSE 9000

# Comando para correr la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]