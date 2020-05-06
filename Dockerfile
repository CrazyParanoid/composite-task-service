FROM openjdk:11-jdk-slim

COPY target/composite-task-service-1.0.jar /opt/composite-task-service/

# some tools
RUN apt-get update && apt-get install -y vim tree mc lnav

# setting proper TZ
ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 8080
VOLUME /opt/composite-task-service/logs

WORKDIR /opt/composite-task-service/

CMD ["java","-jar","composite-task-service-1.0.jar"]