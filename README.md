# Employee Management System (EMS)

This document provides instructions on how to set up and run the Employee Management System (EMS) project locally. The project is built using Spring Boot (v3.3.7) and JSP for the frontend.

## Prerequisites

Before setting up the project, ensure that you have the following installed:

* Java Development Kit (JDK): Version 17 or later
* Apache Maven: Version 3.6 or later
* MySQL Database: Installed and running
* Git: To clone the repository

## Getting Started

### 1. Clone the Repository

Clone the repository using the following command:

```bash
git clone <repository-url>
```

Replace `<repository-url>` with the actual URL of the repository.

### 2. Configure MySQL Database

Create a database in MySQL named `employee_db` using the following command:

```sql
CREATE DATABASE employee_db;
```

Update the `application.properties` file with your MySQL credentials if they are different:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Build the Project

Navigate to the project directory and run the following Maven command to build the project:

```bash
mvn clean install
```

### 4. Run the Application

Run the application using the following command:

```bash
mvn spring-boot:run
```

The application will start on the default port **8080**.

### 5. Access the Application

Open your web browser and navigate to:

```
http://localhost:8080
```

## Project Structure

* `src/main/java`: Contains the Java source code for the backend
* `src/main/webapp/WEB-INF/jsp`: Contains the JSP files for the frontend
* `pom.xml`: Contains the project dependencies and build configuration
* `application.properties`: Contains the application configuration

## Dependencies

The project uses the following dependencies:

* Spring Boot Starter Data JPA: For database interactions
* Spring Boot Starter Validation: For input validation
* Spring Boot Starter Web: For building web applications
* MySQL Connector/J: For connecting to the MySQL database
* Lombok: To reduce boilerplate code
* JSP: For the frontend

## Troubleshooting

### MySQL Connection Issues

* Ensure that the MySQL server is running and accessible
* Verify that the database credentials in `application.properties` are correct

### Port Already in Use

If port 8080 is already in use, change the server port in `application.properties`:

```properties
server.port=9090
```

### JSP Compilation Issues

Ensure that the required JSP dependencies are included in `pom.xml`
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
</dependency>

