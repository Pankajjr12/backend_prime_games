# Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim as build

# Set the working directory inside the container

# Copy the .jar file from the host machine into the container
COPY target/prime-Gaming-store-0.0.1-SNAPSHOT.jar prime-Gaming-store.jar

# Expose the port the application will run on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/prime-Gaming-store.jar"]
