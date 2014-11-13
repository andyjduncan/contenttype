#Sample Content Type Negotiation in Spring MVC

Sample client and server using content type negotiation in Spring MVC.

##Server
Server is a Spring Boot application, run `com.adjectivecolournoun.contenttype.server.Application`.
Uncomment additional controller method in `com.adjectivecolournoun.contenttype.server.controllers.ThingController` to enable version 3 things.

##Client
Client is a Groovy script supporting version one, two and three things.  Run `com/adjectivecolournoun/contenttype/client/GetThings.groovy`
