FROM maven:3.9.8-eclipse-temurin-21-alpine AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=build /app/target/weather-service-0.0.1-SNAPSHOT.jar /app/weather-service.jar
COPY --from=build /app/src/main/resources/ /app/resources/

ENTRYPOINT ["java", "-jar", "/app/weather-service.jar"]
