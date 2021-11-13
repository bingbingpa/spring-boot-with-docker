FROM openjdk:11-jre-slim

ENV DOCKERIZE_VERSION v0.6.1
ENV DB_CONTAINER_NAME db

RUN apt-get update -y && apt-get install -y wget && \
    wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

WORKDIR /root

ARG buildDir=build/unpack

COPY ${buildDir}/lib BOOT-INF/lib
COPY ${buildDir}/app .

CMD dockerize -wait tcp://$DB_CONTAINER_NAME:3306 -timeout 10s java -Dspring.profiles.active=${USER_PROFILE} org.springframework.boot.loader.JarLauncher
