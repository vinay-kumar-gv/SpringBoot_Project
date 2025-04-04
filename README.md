# SpringBoot-Project

#Spring Boot REST API Application
This is a RESTful web application built using Spring Boot,PostgreSQL,JWT Authentication,and Docker.It supports CRUD operations and is tested using Postman.

#Technologies Used
-> Java 21
-> Spring Boot 3.4.4
-> PostgreSQL(Database)
-> Docker & Docker Compose(Containerization)
-> JWT Authentication
-> Swagger(API Documentation)
-> Postman(API Testing)
-> Intellij IDEA(IDE)

#API Documentation
-> Swagger Docs: http://localhost:8080/swagger-ui.html
-> API Docs: http://localhost:8080/api-docs

#Postman Collection
I created a folder called Postman in this repository,where you can find all the Postman API testing files.

#API Endpoints
http://localhost:8080/api
Authentication:
-> POST /auth/register -> Create a new user
-> POST /auth/login -> Authenticate user and receive JWT token

Country API:
http://localhost:8080/api
-> GET /contries -> Get all countries
-> POST /contries -> Add a new country
-> PUT /contries/{id} -> Update a country
-> DELETE /contries{id} -> Delete a country

User API:
http://localhost:8080/api
-> GET /users/me -> Get a user
-> PUT /users/me -> Update a user
-> DELETE /users/me/{id} -> Delete a user





