#FROM openjdk:8
#EXPOSE 8080
#ADD target/allInOnce.jar allInOnce.jar
#ENTRYPOINT ["java","-jar","/allInOnce.jar"]

FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/allInOnce.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]