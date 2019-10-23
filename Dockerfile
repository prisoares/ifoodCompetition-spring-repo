FROM openjdk:8

WORKDIR /usr/src/api

COPY gradle/ gradle/
COPY build.gradle settings.gradle gradlew ./

RUN ./gradlew --version

COPY src/ src/
