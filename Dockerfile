FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app

COPY pom.xml .
COPY startup-logger-starter/pom.xml startup-logger-starter/
COPY fmse-app/pom.xml fmse-app/

COPY startup-logger-starter/src startup-logger-starter/src
COPY fmse-app/src fmse-app/src

RUN --mount=type=cache,target=/root/.m2 mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/fmse-app/target/fmse-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
