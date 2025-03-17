FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /opt
EXPOSE 8080
RUN mvn clean compile package -DskipTests
COPY target/*.jar /opt/myapp.jar
ENTRYPOINT exec java $JAVA_OPT -jar myapp.jar
