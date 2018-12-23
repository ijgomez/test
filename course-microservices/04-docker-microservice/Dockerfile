FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER igomez
ADD target/course-docker-microservice-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]