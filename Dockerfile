FROM openjdk:17.0.2-jdk-slim-buster
WORKDIR /opt/app
ARG JAR_FILE=target/counter-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8095

CMD ["java", "-jar", "app.jar"]

