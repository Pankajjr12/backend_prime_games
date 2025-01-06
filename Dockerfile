# Use OpenJDK 17 as the base image
FROM openjdk:17-slim AS build

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code to the working directory
COPY pom.xml /app
COPY src /app/src

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Use a smaller image to run the app
FROM openjdk:17-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the build image
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app will run on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
