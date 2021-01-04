FROM openjdk:15-slim-buster
RUN apt-get update && apt-get install -y netcat
ADD build/libs/media-service-0.0.1.jar mediaService.jar
EXPOSE 8081:8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","mediaService.jar"]