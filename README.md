Overview
This project provides a REST API for managing user accounts and transactions. It
includes endpoints for account creation, retrieving user details, and handling
transactions.
Explanation:
1. REST API: Customer Controller exposes endpoints for creating accounts and
retrieving user information.
2. Account Service: Handles account creation and retrieving balance.
3. Transaction Service: Handles transaction creation and fetching transaction
history.
4. Database: Stores accounts and transactions. This implementation as the in-
memory database. But as a future enhancement, any type of a database or
cloud storage can be used by just introducing an implementation of &lt;&lt;Base&gt;&gt;
class.
5. Tests and Verification: Jococo plugin has been used to verify the test
coverage. Currently without 80% of test coverage build won’t compile.
6. CI/CD : Github workflow file has been added to the project to automatically
build , test and deploy the application to Dockerhub. These actions are
automatically triggered when pull request is merged to dev or main branches.

Prerequisites
Ensure you have the following installed:
 Java 17+
 Maven 3+
 Docker

How to Run Using Maven
To build and run the project using Maven, use the following commands:
mvn clean install
mvn spring-boot:run

Docker Image Location
The Docker image for this project is available on DockerHub:
docker pull anujahiniduma/anu:latest

Running with Docker
To run the application using Docker:
docker pull your-dockerhub-username/api-project:latest

Swagger Documentation
After starting the application, you can access the Swagger UI at:
http://localhost:8080/swagger-ui.html

REST API Endpoints
Account Management
 POST /api/accounts - Create a new account
 GET /api/users/{customerId} - Retrieve user details including balance and
transactions
Transactions
 POST /api/transactions - Create a transaction
 GET /api/transactions/{accountId} - Get transaction history for an account

Spring Boot Actuator Endpoints

Spring Boot Actuator provides system monitoring endpoints:
 /actuator/health - Application health status
 /actuator/info - Application information
 /actuator/metrics - Performance metrics

JaCoCo Test Reports
JaCoCo generates test coverage reports. To generate the reports:
mvn clean verify

Reports will be available in:
target/site/jacoco/index.html
