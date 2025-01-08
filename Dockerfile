# Step 1: Build the project using Maven and OpenJDK 17
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container to /app
WORKDIR /app

# Copy the entire project to the /app directory in the container
COPY . .

# Run Maven to build the project and create the jar file (skip tests)
RUN mvn clean package -DskipTests

# Step 2: Create a smaller image for running the application using OpenJDK 17
FROM openjdk:17.0.1-jdk-slim

# Set the working directory in the second stage container
WORKDIR /app

# Copy the JAR file from the first stage (from /target) to the second stage (into /app)
COPY --from=build /app/target/prime-Gaming-store-0.0.1-SNAPSHOT.jar prime-Gaming-store.jar

# Expose port 8080 for the application
EXPOSE 5353

# Define the entry point to run the Spring Boot application using Java
ENTRYPOINT ["java", "-jar", "prime-Gaming-store.jar"]
