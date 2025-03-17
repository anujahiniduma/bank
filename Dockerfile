FROM tomcat:10-jdk17
WORKDIR /opt
EXPOSE 8080
RUN mvn clean compile package -DskipTests
COPY target/*.jar /opt/myapp.jar
ENTRYPOINT exec java $JAVA_OPT -jar myapp.jar
