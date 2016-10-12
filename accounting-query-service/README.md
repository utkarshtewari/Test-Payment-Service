## Overview
Accounting Query Service acts as an event-listener and a view. It listens for the `Events` being emitted by the command-side and processes them. In this example, the query-side simply builds and maintains a ‘materialised view’ or ‘projection’ which holds the latest state of the individual Account. 

The query-side can be replicated many times for scalability and the messages held by the RabbitMQ queues can be made to be durable, so they can even temporarily store messages on behalf of the query-side if it goes down.

## Running the application
* Build the application by running the command: mvn clean install
* Run the application by running the command: java -jar target/accounting-command-service-0.0.1-SNAPSHOT.jar

## External Configuration
The project derives it's configuration from the configuration-service. We have defined the spring.cloud.config.uri in the bootstrap.yml file and that tells the application where to pick up the external configurations. In our case, the URL points to the running configuration-service (http://localhost:8888). 

Important dependencies in classpath
* spring-cloud-config-client dependency so that the application can consume the config server
* spring-cloud-starter-eureka dependecy to register the service in discovery server 