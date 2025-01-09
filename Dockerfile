FROM maven:3.8.4-openjdk-17 as maven-builder
COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests
FROM openjdk:17-alpine

RUN "ls"

COPY --from=maven-builder target/*.jar /app-service/godelivery.jar
WORKDIR /app-service

EXPOSE 8080
ENTRYPOINT ["java","-jar","godelivery.jar"]