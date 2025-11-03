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

# Cambia el nombre del JAR para que coincida con maestros-service/pom.xml
COPY --from=build /app/target/maestros-service-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8081
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]