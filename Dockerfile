FROM openjdk:17-oracle
WORKDIR /app/
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/

RUN mvn clean package 
COPY target/booting-web-${VERSION}.jar target/application.jar


FROM openjdk:17-oracle
WORKDIR /app/

COPY --from=BUILDER /build/target/application.jar /app/

CMD java -jar /app/application.jar