## Overview
Accounting Command Service contains all the domain logic and business rules. It consumes PaymentScreenedEvent and generates AccountUpdatedEvent which are persisted into EventStore (MongoDB) and propagated out to other processes via RabbitMQ messaging.

## Running the application
* Build the application by running the command: mvn clean install
* Run the application by running the command: java -jar target/accounting-command-service-0.0.1-SNAPSHOT.jar

## External Configuration
The project derives it's configuration from the configuration-service. We have defined the spring.cloud.config.uri in the bootstrap.yml file and that tells the application where to pick up the external configurations. In our case, the URL points to the running configuration-service (http://localhost:8888). 

Important dependencies in classpath
* spring-cloud-config-client dependency so that the application can consume the config server
* spring-cloud-starter-eureka dependecy to register the service in discovery server 