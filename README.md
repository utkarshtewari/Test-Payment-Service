## Reacive Microservices
This is an operational model for Reactive Microservices Architecture with Event Sourcing and Command Query Responsibility Separation design patterns.

## Table of Contents
* [Application Architecture](#application-architecture)
* [Architecture Components](#architecture-components)

## <a name="application-architecture"></a> Application Architecture
Architecture consists of 3 business services and backing services from Pilot-Microservices such as [Configuration Service] (https://code.cognizant.com/mrudul-palvankar/Pilot-Microservices/blob/master/configuration-service/README.md) and [Discovery Service] (https://code.cognizant.com/mrudul-palvankar/Pilot-Microservices/blob/master/discovery-service/README.md). Each component is built separately using their own build file. 

![Reactive Microservices Architecture](Janus-ReactiveMicroservices.png)

## <a name="architecture-components"></a> Architecture Components
* Payments Service
* Screening Service
* Accounting Service

## Using the Application

#### Running on local machine
* You can build the projects by maven. 
    * Maven: Run maven at the parent project "Janus-ReactiveMicroservices", this will build all the individual projects. Run the  individual project jar by running the command: mvn spring-boot:run

* You can run the applications in the order listed below.
    * Configuration Service - This application should be run first as it holds properties for all applications 
    * Discovery Service - This application should be run second as all the services register themselves with discovery server
    * All other services such as Payments Service, Screening Service and Accounting Service

* Please refer to the individual readme files on instructions of how to run the services. 