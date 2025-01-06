# Use the Maven image to build the Spring Boot app
# FROM openjdk-17 AS build

# # Expose the port the app will run on
# EXPOSE 8080

# # Copy the JAR file from the build stage
# ADD prime-gaming-store/target/spring-boot-docker.jar /spring-boot-docker.jar


# # Command to run the Spring Boot app
# ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar"]
