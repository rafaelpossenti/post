FROM openjdk:8-jdk-alpine
MAINTAINER rafael.possenti
COPY build/libs/post-0.0.1-SNAPSHOT.jar post-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/post-0.0.1-SNAPSHOT.jar"]