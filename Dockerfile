# Step 1: Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim as build

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Step 4: Run Maven to build the application (skip tests to speed up the build)
RUN ./mvnw clean package -DskipTests

# Step 5: Use a smaller image for running the application
FROM openjdk:17-jdk-slim

# Step 6: Copy the built JAR file from the previous step
COPY --from=build /app/target/prime-Gaming-store-0.0.1-SNAPSHOT.jar /prime-Gaming-store.jar

# Step 7: Expose the port that Spring Boot will run on
EXPOSE 8080

# Step 8: Run the application
ENTRYPOINT ["java", "-jar", "/prime-Gaming-store.jar"]
