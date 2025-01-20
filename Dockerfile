# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS maven-builder
WORKDIR /app
COPY pom.xml .
# Download dependencies to cache this layer if the dependencies haven't changed
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-alpine
WORKDIR /app
# Copy the JAR file from the build stage
COPY --from=maven-builder /app/target/*.jar godelivery.jar

EXPOSE 4444
ENTRYPOINT ["java", "-jar", "godelivery.jar"]
