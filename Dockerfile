# Use the Maven image to build the Spring Boot app
FROM maven:3.9.0-jdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies (optional step for faster rebuilds)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code to the container
COPY src /app/src

# Build the Spring Boot app (create the JAR file)
RUN mvn clean package -DskipTests

# Use a smaller base image for the runtime environment (JRE)
FROM openjdk:17-jdk-slim

# Set the working directory for runtime
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app will run on
EXPOSE 8080

# Command to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
