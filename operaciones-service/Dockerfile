# Etapa de Build (Construcción)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Etapa de Run (Ejecución)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Cambia el nombre del JAR para que coincida con operaciones-service/pom.xml
COPY --from=build /app/target/operaciones-service-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8082
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]