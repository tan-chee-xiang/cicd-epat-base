# first stage
FROM eclipse-temurin:25.0.1_8-jdk-alpine-3.23 AS builder

WORKDIR /app

COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY src src
COPY pom.xml .
RUN ./mvnw package -DskipTests=true

# second stage
FROM eclipse-temurin:25.0.2_10-jre-alpine-3.23

WORKDIR /runningapp

COPY --from=builder /app/target/d13revision-0.0.1-SNAPSHOT.jar .

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

CMD ["java", "-jar", "d13revision-0.0.1-SNAPSHOT.jar"]
