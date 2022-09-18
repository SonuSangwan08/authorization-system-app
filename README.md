# authorization-system-app

A permission based authorization system for API access control

Requirements:-
Java 11
PostgresSQL DB
Please update dbname,username and password in application.properties
Current configuration is given below:-
spring.datasource.url=jdbc:postgresql://localhost:5432/authorization-system
spring.datasource.username=postgres
spring.datasource.password=sonu

Command to run application in local==>

./mvnw spring-boot:run

Application is configured to run at port 8085.

Application Url: http://localhost:8085/authorization-system/api

Swagger Url : http://localhost:8085/authorization-system/api/swagger-ui.html

Response type - JSON

Basic functionalities:-(Please go through the API documentation of swagger to check API details)
/auth/register - Register new user
/auth/login - Login with username/password

Below Rest controllers contains 2 public endpoints also, but rest of the endpoints require
Bearer type token, so please make sure to Login to get token.

/content-board - Few endpoints to demonstrate Role based access
/book - Few endpoints to demonstrate fine-grained role-privileges based access

Roles supported in app -> admin, user

Various validations exists for fields like Username, Email, Password etc. These can be
reviewed in Swagger models.



Location of application jar:- target/authorization-system-1.0.0.jar
Running application jar :- java -jar location-of-app-jar



