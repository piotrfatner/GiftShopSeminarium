# :hibiscus: Gift shop

E-commerce project developed using Spring Boot and React.js.<br>


## Used Technologies:

* Back-end: Spring (Boot, Data, Security), JPA / Hibernate, JUnit, Mockito
* Front-end: TypeScript, React.js, Jest, Bootstrap, CSS
* Security: JWT, OAuth2 Google, Facebook, Github
* REST API
* AWS: EC2, S3, PostgreSQL RDS
* Server Build: Maven
* Client Build: npm, webpack

## Features

* Authentication with JWT and Email validation.
* Customers can search for the product according to the specified criteria.
* Customers can add and delete products from the shopping cart.
* Customers can order the products in the shopping cart.

## Installation

1. Install Java 8
2. Install maven 3
3. Install mysql
4. Install Lombok and GraphQL plugins
5. Create a new DB (perfume) in MySQL
6. In file application.properties: <br/>
   6.1 Change your upload path to directory .../ecommerce-spring-reactjs/src/main/resources/uploads (variable upload.path (10 line)) <br/>
   6.2 Type your username and password from your gmail account on 14 and 16 lines. <br/>
   6.3 Go to https://myaccount.google.com/u/2/lesssecureapps and change to: “Allow less secure apps: ON”.
   If you do not change this setting in your Google account, then when sending a message to an email, a 500 server error will occur. <br/>
7. Install node.js
8. Type in console command: npm install

## Swagger Documentation

http://ec2-3-122-228-86.eu-central-1.compute.amazonaws.com:8080/swagger-ui.html <br/>
Or show local: <br/>
http://localhost:8080/swagger-ui.html


