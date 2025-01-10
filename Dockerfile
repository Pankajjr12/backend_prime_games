# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the local machine to the container
COPY target/prime-Gaming-store-0.0.1-SNAPSHOT.jar springprimeapp.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/springprimeapp.jar"]

# Expose the application port (default 8080)
EXPOSE 8080


