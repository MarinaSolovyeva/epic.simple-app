FROM openjdk:17.0.2-jdk-slim-buster
WORKDIR /opt/app
ARG JAR_FILE=target/counter-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

COPY wait-for-it.sh /usr/wait-for-it.sh
RUN chmod +x /usr/wait-for-it.sh

EXPOSE 8095

CMD ["/usr/wait-for-it.sh", "mysql-db:3306", "--", "java", "-jar", "app.jar"]

