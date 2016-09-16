## Reactive Microservices
The high level of autonomy and isolation present in the Microservices Architecture pattern has the biggest impact on the design and the architecture. It also presents us with a challenge of building resilient applications and distributed data management. 

A microservice must react to failure, react to load and react to user. It should also be able to manage data consistency using transactions that are distributed across a network of many different applications and databases.

To achieve the above properties, it is important to have an asynchronous boundary, that can provide decoupling of services. There are tried and tested patterns for successfully building and maintaining complex distributed systems using Event Sourcing and Command Query Responsibility Segregation (CQRS). 

This subproject uses event driven architecture to decouple the services and uses Event Sourcing and Command Query Responsibility Segregation (CQRS) to achieve data consistency.

## Table of Contents
* [Application Architecture](#application-architecture)
* [Architecture Components](#architecture-components)
* [Reactive Principles] (#reactive-principles) 
* [Event Sourcing] (#event-sourcing) 
* [Command Query Responsibility Segregation] (#cqrs)

## <a name="application-architecture"></a> Application Architecture
Architecture consists of 3 business services and backing services from Pilot-Microservices such as [Configuration Service] (https://code.cognizant.com/mrudul-palvankar/Pilot-Microservices/blob/master/configuration-service/README.md) and [Discovery Service] (https://code.cognizant.com/mrudul-palvankar/Pilot-Microservices/blob/master/discovery-service/README.md). Each component is built separately using their own build file.  

* Payments Service accepts a payment for execution. It creates an event PaymentAddedEvent in event store and publishes the same on event bus. It also consumes events published by Screening service and Accounting Service to update the payment events and the state of the payment being executed.
* Screening Service which is responsible for screening a payment, subscribes for PaymentAddedEvent, consumes the same and publishes PaymentScreenedEvent. 
* Accounting Service responsible for accouting a payment, consumes PaymentScreenedEvent and publishes PaymentAccountedEvent.

![Reactive Microservices Architecture](Janus-ReactiveMicroservices.png)

## <a name="architecture-components"></a> Architecture Components
* Payments Service
* Screening Service
* Accounting Service

## <a name="reactive-principles"></a> Reactive Principles 
*

## <a name="event-scourcing"></a> Event Sourcing 
* Event Sourcing is architecture pattern that represents state as series of events.

## <a name="cqrs"></a> Command Query Responsibility Segregation
*

## Using the Application

#### Running on local machine
* You can build the projects by maven. 
    * Maven: Run maven at the parent project "Janus-ReactiveMicroservices", this will build all the individual projects. Run the  individual project jar by running the command: mvn spring-boot:run

* You can run the applications in the order listed below.
    * Configuration Service - This application should be run first as it holds properties for all applications 
    * Discovery Service - This application should be run second as all the services register themselves with discovery server
    * All other services such as Payments Service, Screening Service and Accounting Service

* Please refer to the individual readme files on instructions of how to run the services. 