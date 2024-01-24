FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} url-shortener.jar
ENTRYPOINT ["java", "-jar", "/url-shortener.jar"]
EXPOSE 8089