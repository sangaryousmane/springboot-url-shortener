# Use the official maven/Java 11 image to create a build artifact.
FROM maven:3.8.3-openjdk-11 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install


FROM adoptopenjdk:11-jre-hotspot

# Set the working directory in the container
WORKDIR /app

COPY --from=builder /app/target/*.jar ./app.jar


EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
