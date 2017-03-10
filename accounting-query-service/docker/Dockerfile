FROM java:8
VOLUME /tmp
EXPOSE 12000 12002
ADD build/accounting-query-service-0.0.1-SNAPSHOT.jar accounting-query-service.jar
RUN bash -c 'touch /accounting-query-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/accounting-query-service.jar"]

