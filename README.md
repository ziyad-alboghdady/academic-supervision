# Academic Supervision System API

A RESTful backend application built with **Spring Boot** and **PostgreSQL** for managing academic supervision data in a university-style environment.

This project provides CRUD operations for three main modules:

- **Advisers**
- **Studies**
- **Supervises**

It follows a clean layered architecture using **Controller**, **Service**, **Repository**, **DTO**, and **Model** layers, with validation and global exception handling to improve reliability and maintainability.

---

## Features

- RESTful API design
- Full CRUD operations for all main entities
- Layered architecture for better separation of concerns
- DTO mapping for cleaner API responses
- Input validation using Jakarta Validation
- Global exception handling for consistent error responses
- PostgreSQL database integration with Spring Data JPA
- Swagger / OpenAPI documentation support
- Lombok integration to reduce boilerplate code

---

## Tech Stack

- **Java 21**
- **Spring Boot 4**
- **Spring Web MVC**
- **Spring Data JPA**
- **PostgreSQL**
- **Jakarta Validation**
- **Lombok**
- **Springdoc OpenAPI / Swagger UI**
- **Maven**

---

## Project Architecture

The project is organized using a standard layered backend structure:

- **Controller Layer**  
  Handles HTTP requests and responses

- **Service Layer**  
  Contains business logic

- **Repository Layer**  
  Handles database access using Spring Data JPA

- **DTO Layer**  
  Transfers clean data between backend and client

- **Model Layer**  
  Defines JPA entities and relationships

- **Exception Layer**  
  Centralizes error handling and custom exceptions

---

## Main Entities

### 1. Advisers
Represents academic advisers.

**Fields:**
- `id`
- `name`
- `department`

### 2. Studies
Represents academic studies or research topics.

**Fields:**
- `id`
- `title`
- `description`

### 3. Supervises
Represents the supervision relationship between an adviser and a study, including student performance.

**Fields:**
- `supervisesId`
- `student`
- `performance`
- `adviser`
- `study`

---

## Relationships

- One **Adviser** can supervise many records in **Supervises**
- One **Study** can appear in many records in **Supervises**
- **Supervises** acts as the linking entity between **Advisers** and **Studies**

This design allows the system to represent supervision assignments clearly while keeping the data normalized.

---

## Validation

The project uses **Jakarta Validation** annotations such as:

- `@NotBlank`
- `@Size`
- `@Min`
- `@Max`
- `@Valid`

Examples:
- Adviser name cannot be empty
- Study title must be between 3 and 50 characters
- Performance must be between 0 and 100

---

## Exception Handling

The application includes custom exception handling using:

- `ResourceNotFoundException`
- `ResourceAlreadyExistsException`
- `GlobalExceptionHandler`

This provides structured error responses with:
- HTTP status code
- descriptive error message

---

## API Endpoints

## Advisers

Base path: `/adviser`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/adviser/all` | Get all advisers |
| GET | `/adviser/get/{id}` | Get adviser by ID |
| POST | `/adviser/add` | Create a new adviser |
| PUT | `/adviser/update/{id}` | Update an adviser |
| DELETE | `/adviser/delete/{id}` | Delete an adviser |

---

## Studies

Base path: `/studies`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/studies/all` | Get all studies |
| GET | `/studies/get/{id}` | Get study by ID |
| POST | `/studies/add` | Create a new study |
| PUT | `/studies/update/{id}` | Update a study |
| DELETE | `/studies/delete/{id}` | Delete a study |

---

## Supervises

Base path: `/supervises`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/supervises/all` | Get all supervision records |
| GET | `/supervises/get/{id}` | Get supervision record by ID |
| POST | `/supervises/add` | Create a new supervision record |
| PUT | `/supervises/update/{id}` | Update a supervision record |
| DELETE | `/supervises/delete/{id}` | Delete a supervision record |

---

## Swagger API Documentation

After running the application, you can access the API documentation using Swagger UI:

- Swagger UI: http://localhost:8080/swagger-ui/index.html  
- OpenAPI JSON: http://localhost:8080/v3/api-docs

> Note: In newer Spring Boot versions (Springdoc OpenAPI 3+), `/swagger-ui.html` may not work. Use `/swagger-ui/index.html` instead.

---

## Database Configuration

The project is configured to use **PostgreSQL**.

Example configuration in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/DIMS1
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```
Make sure PostgreSQL is installed, and update the username/password according to your local setup.
Getting Started
1. Clone the repository
git clone https://github.com/your-username/academic-supervision.git
cd academic-supervision
2. Configure the database

Create a PostgreSQL database:

CREATE DATABASE DIMS1;

Then update your credentials inside application.properties.

3. Build and run the project

Using Maven Wrapper:

./mvnw spring-boot:run

On Windows:

mvnw.cmd spring-boot:run

Or using Maven:

mvn spring-boot:run
4. Access the application
API Base URL: http://localhost:8080
Swagger UI: http://localhost:8080/swagger-ui/index.html
Example JSON Requests
Create Adviser
{
  "name": "Dr. Ahmed Hassan",
  "department": "Computer Science"
}
Create Study
{
  "title": "AI in Education",
  "description": "A study about using artificial intelligence in educational systems"
}
Create Supervises
{
  "student": "Mohamed Ali",
  "performance": 90,
  "adviserId": 1,
  "studyId": 1
}

Note: Supervises depends on existing Adviser and Study records.

## Project Structure

```bash
src
├── main
│   ├── java/org/example/academic_supervision
│   │   ├── Controller
│   │   ├── DTO
│   │   ├── Exception
│   │   ├── Model
│   │   ├── Repository
│   │   ├── Service
│   │   └── AcademicSupervisionApplication.java
│   └── resources
│       └── application.properties
└── test
    └── java/org/example/academic_supervision
```
## What This Project Demonstrates

- Building REST APIs with Spring Boot  
- Designing JPA entity relationships  
- Applying validation rules  
- Using layered architecture (Controller → Service → Repository)  
- Handling exceptions professionally  
- Integrating PostgreSQL with Hibernate  
- Documenting APIs using Swagger  

---

## Future Improvements

- Add unit and integration testing  
- Implement pagination and filtering  
- Improve DTO usage across all endpoints  
- Add authentication (Spring Security + JWT)  
- Add audit fields (createdAt, updatedAt)  
- Enhance logging and monitoring  
- Dockerize the application  

---

## Author

Developed as part of an academic backend project using Spring Boot and PostgreSQL.
