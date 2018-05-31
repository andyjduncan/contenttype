# Sample Content Type Negotiation in Spring MVC

Sample client and server using content type negotiation in Spring MVC.

## Server
Server is a Spring Boot application, run `./gradlew server:bootRun` to start it.
Uncomment additional controller method in `com.adjectivecolournoun.contenttype.server.controllers.ThingController` to enable version 3 `Thing`s.

A [Swagger](http://swagger.io/) UI describing the API is available at [http://localhost:8080/sdoc.jsp](http://localhost:8080/sdoc.jsp)
courtesy of [swagger-springmvc](https://github.com/springfox/springfox).

## Client
Client is a Groovy script supporting version one, two and three `Thing`s.  Run `./gradlew client:runClient` to request `Thing`s from server
