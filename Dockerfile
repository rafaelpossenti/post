FROM openjdk:8-jdk-alpine

# VOLUME /tmp
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh wait-for-it.sh
RUN /bin/sh -c "chmod +x wait-for-it.sh"

EXPOSE 8081
MAINTAINER rafael.possenti
COPY build/libs/post-0.0.1-SNAPSHOT.jar post-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/post-0.0.1-SNAPSHOT.jar"]
