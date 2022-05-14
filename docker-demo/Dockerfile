FROM openjdk:8-jdk-alpine
MAINTAINER dhavalshankh.md@gmail.com
VOLUME /tmp
RUN addgroup -S "docker-demo"
RUN adduser -S maheshd -G "dockerdemo"
ARG APP_NAME=docker-demo
RUN mkdir -p /home/mahesh/app
ARG JAR_FILE=target/${APP_NAME}-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /home/mahesh/app/docker-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/home/mahesh/app/docker-demo-0.0.1-SNAPSHOT.jar"]
