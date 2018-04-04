FROM openjdk:8-jdk-alpine
VOLUME /mnt/data
ARG JAR_FILE
ADD target/sql-migration-0.0.1-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]