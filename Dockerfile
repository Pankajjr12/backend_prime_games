FROM maven:3.8.5-openjdk-17 AS build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /target/prime-Gaming-store-0.0.1-SNAPSHOT.jar prime-Gaming-store.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","prime-Gaming-store.jar"]


