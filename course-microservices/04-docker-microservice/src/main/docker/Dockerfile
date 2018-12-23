FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER javier.izquierdo.gomez@gmail.com
ADD target/microdocker-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]