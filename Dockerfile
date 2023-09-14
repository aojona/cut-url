FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /app
EXPOSE 8080
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} job-scanner.jar
ENTRYPOINT ["java", "-jar", "/app/job-scanner.jar"]