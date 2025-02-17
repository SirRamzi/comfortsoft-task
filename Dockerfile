# Сборка
FROM maven:3.8.6-eclipse-temurin-18 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

# Запуск
FROM eclipse-temurin:18-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/comfortsoft-task-*.jar app.jar
COPY src/test/resources ./src/test/resources
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]