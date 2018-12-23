FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER ijgomez
ADD target/course-docker-eureka-server-1.0.jar eureka.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/eureka.jar"]