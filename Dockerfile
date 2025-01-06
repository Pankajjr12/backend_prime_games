# Use the Maven image to build the Spring Boot app
FROM maven:3.8.5-jdk-17 AS build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080

ENTRYPOINT [ "java","-jar","demo.jar" ]

