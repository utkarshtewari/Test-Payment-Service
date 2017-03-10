FROM java:8
VOLUME /tmp
EXPOSE 9095 9097
ADD build/accounting-command-service-0.0.1-SNAPSHOT.jar accounting-command-service.jar
RUN bash -c 'touch /accounting-command-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/accounting-command-service.jar"]

