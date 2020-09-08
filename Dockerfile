# Alpine Linux with OpenJDK JRE
FROM adoptopenjdk/openjdk11:alpine-jre

# Copy jar file
COPY target/*.jar  /opt/demo.jar

# run the app
CMD ["java", "-jar", "/opt/demo.jar"]