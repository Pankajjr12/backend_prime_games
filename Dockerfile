# Stage 1: Build the application using Maven
FROM maven:3.8.4-openjdk-17 as maven-builder

WORKDIR /app
COPY src /app/src
COPY pom.xml /app

# Build the application and skip tests
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime container
FROM openjdk:17-alpine

# Create the working directory for the app
WORKDIR /app-service

# Copy the built JAR file from the Maven builder
COPY --from=maven-builder /app/target/*.jar /app-service/godelivery.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "godelivery.jar"]
