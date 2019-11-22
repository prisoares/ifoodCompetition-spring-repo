FROM openjdk:8

# Copy local code to the container image.
WORKDIR /usr/src/api

COPY gradle/ gradle/
COPY build.gradle settings.gradle gradlew ./

RUN ./gradlew --version

COPY src/ src/

# Build a release artifact.
RUN ./gradlew build
