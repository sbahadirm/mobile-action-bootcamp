FROM postgres
ENV POSTGRES_PASSWORD docker
ENV POSTGRES_DB mobileaction

FROM openjdk:17-alpine
ARG JAR_FILE=target/mobile-action-bootcamp-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]