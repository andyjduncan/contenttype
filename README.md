#Sample Content Type Negotiation in Spring MVC

Sample client and server using content type negotiation in Spring MVC.

##Server
Server is a Spring Boot application, run `./gradlew server:bootRun` to start it.
Uncomment additional controller method in `com.adjectivecolournoun.contenttype.server.controllers.ThingController` to enable version 3 `Thing`s.

##Client
Client is a Groovy script supporting version one, two and three `Thing`s.  Run `./gradlew client:runClient` to request `Thing`s from server
