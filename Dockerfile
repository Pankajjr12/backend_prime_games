FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/prime-Gaming-store-0.0.1-SNAPSHOT.jar prime-Gaming-store.jar
ENTRYPOINT ["java","-jar","/prime-Gaming-store.jar"]


