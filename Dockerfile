# Build stage
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install -y openjdk-19-jdk
COPY . /app
WORKDIR /app

# Set environment variable for Gradle build
ENV JAVA_HOME=/usr/lib/jvm/java-19-openjdk-amd64
RUN ./gradlew bootJar --no-daemon

FROM openjdk:19-jdk-slim

EXPOSE 8080

COPY --from=build /app/build/libs/IdentityReconciliation-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]