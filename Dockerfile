# Alpine Linux with OpenJDK JRE
FROM openjdk:8
# Copy jar file
COPY target/*.jar  /opt/demo.jar
ADD wrapper.sh wrapper.sh

RUN bash -c 'chmod +x /wrapper.sh'
ENTRYPOINT ["/bin/bash", "/wrapper.sh"]
