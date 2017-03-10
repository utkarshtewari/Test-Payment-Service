FROM java:8
VOLUME /tmp
EXPOSE 9090 9092
ADD build/payments-service-0.0.1-SNAPSHOT.jar payments-service.jar
RUN bash -c 'touch /payments-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/payments-service.jar"]

