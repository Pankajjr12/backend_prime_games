# Use an official OpenJDK runtime as a parent image
FROM java:8

EXPOSE 8080
# Copy the jar file from the local machine to the container
ADD target/springprimeapp.jar springprimeapp.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "springprimeapp.jar"]

# Expose the application port (default 8080)



