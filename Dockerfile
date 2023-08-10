# Build stage
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install -y openjdk-17-jdk
COPY . /app
WORKDIR /app

RUN ./gradlew bootJar --no-daemon -Dorg.gradle.java.home=/usr/lib/jvm/openjdk-17

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /build/libs/IdentityReconciliation-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]