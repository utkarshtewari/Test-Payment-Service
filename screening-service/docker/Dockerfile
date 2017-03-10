FROM java:8
VOLUME /tmp
EXPOSE 9080 9082
ADD build/screening-service-0.0.1-SNAPSHOT.jar screening-service.jar
RUN bash -c 'touch /screening-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/screening-service.jar"]

