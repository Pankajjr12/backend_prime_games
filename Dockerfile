# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS maven-builder
WORKDIR /app
COPY pom.xml . 
RUN mvn dependency:go-offline && \
    mvn clean package -DskipTests

COPY src ./src

# Stage 2: Create the runtime image
FROM openjdk:17-slim
WORKDIR /app
COPY --from=maven-builder /app/target/*.jar godelivery.jar

EXPOSE 4444
ENTRYPOINT ["java", "-jar", "godelivery.jar"]
