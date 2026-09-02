# Product REST API

A secure and scalable Product REST API developed using **Java 17, Spring Boot, Spring Data JPA, MySQL, Spring Security, JWT, Docker, and Swagger/OpenAPI**.

The application provides CRUD operations for products and supports authentication, authorization, role-based access control, validation, pagination, refresh-token rotation, and automated testing.

---

## 🚀 Technologies Used

* Java 17
* Spring Boot 3
* Spring Data JPA / Hibernate
* Spring Security
* JWT Authentication
* MySQL 8
* Maven
* Docker
* Docker Compose
* Swagger / OpenAPI
* JUnit 5
* Mockito
* H2 Database
* Jakarta Bean Validation

---

## 📁 Project Structure

```text
product-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/productapi/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── entity/
│   │   │       ├── exception/
│   │   │       ├── repository/
│   │   │       ├── security/
│   │   │       └── service/
│   │   └── resources/
│   │       └── application.yml
│   │
│   └── test/
│       └── java/
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── README.md
└── .gitignore
```

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
Client
   │
   ▼
REST Controller
   │
   ▼
Service Layer
   │
   ▼
Repository Layer
   │
   ▼
MySQL Database
```

### Main Layers

**Controller Layer**

* Handles HTTP requests and responses.
* Provides REST endpoints under `/api/v1/`.

**Service Layer**

* Contains business logic.
* Handles product CRUD operations and authentication.

**Repository Layer**

* Uses Spring Data JPA.
* Communicates with the database.

**Security Layer**

* Implements Spring Security and JWT authentication.
* Supports USER and ADMIN roles.
* Protects secured endpoints.

**Database Layer**

* MySQL is used for persistent storage.
* JPA/Hibernate manages database entities and relationships.

---

## 🔐 Authentication

The API uses JWT-based authentication.

### Registration

```http
POST /api/v1/auth/register
```

Request:

```json
{
  "username": "anand",
  "password": "Password@123"
}
```

### Login

```http
POST /api/v1/auth/login
```

Request:

```json
{
  "username": "anand",
  "password": "Password@123"
}
```

The login response provides an access token and refresh token.

Use the access token in secured requests:

```text
Authorization: Bearer <access-token>
```

### Role-Based Access

* `USER` – Can access permitted read operations.
* `ADMIN` – Can create, update, and delete products.

---

## 📦 Product API Endpoints

| Method | Endpoint                      | Description             |
| ------ | ----------------------------- | ----------------------- |
| GET    | `/api/v1/products`            | Get all products        |
| GET    | `/api/v1/products/{id}`       | Get product by ID       |
| POST   | `/api/v1/products`            | Create product          |
| PUT    | `/api/v1/products/{id}`       | Update product          |
| DELETE | `/api/v1/products/{id}`       | Delete product          |
| GET    | `/api/v1/products/{id}/items` | Get items for a product |

---

## 🗄️ Database Schema

### Product

```text
product
--------------------------------
id
product_name
created_by
created_on
modified_by
modified_on
```

### Item

```text
item
--------------------------------
id
product_id
quantity
```

`item.product_id` is a foreign key referencing `product.id`.

Indexes are used on frequently queried fields such as product name, creation date, and product ID.

---

## 🐳 Running With Docker

### Prerequisites

Install:

* Docker Desktop
* Git

### Start the application

Clone the repository:

```bash
git clone https://github.com/thombreanand/product-api.git
cd product-api
```

Start the application:

```bash
docker compose up --build
```

The application will start on:

```text
http://localhost:8080
```

MySQL runs inside the Docker Compose network.

To stop the application:

```bash
docker compose down
```

---

## 📖 Swagger API Documentation

After starting the application, open:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger provides an interactive interface for testing all API endpoints.

### Recommended testing flow

1. Register a user.
2. Login.
3. Copy the JWT access token.
4. Click **Authorize** in Swagger.
5. Enter:

```text
Bearer <access-token>
```

6. Test the Product APIs.

---

## 🧪 Testing

The project includes automated tests using:

* JUnit 5
* Mockito
* Spring Boot Test
* H2 Database
* Spring Security Test

Run tests using Maven:

```bash
mvn test
```

---

## ⚙️ Configuration

Application configuration is available in:

```text
src/main/resources/application.yml
```

Important configuration includes:

* Database URL
* Database username/password
* Server port
* JWT configuration
* CORS configuration
* HTTPS enforcement

For production deployments, sensitive credentials and JWT secrets should be provided through environment variables rather than committed to source control.

---

## 🌐 CORS

CORS is configured to allow requests from the configured frontend origin.

The default development configuration supports:

```text
http://localhost:3000
```

---

## 🔒 Security Features

The API includes:

* JWT authentication
* Refresh token rotation
* BCrypt password hashing
* Role-Based Access Control
* Protected REST endpoints
* CORS configuration
* CSRF configuration for stateless API usage
* Global exception handling
* Request validation
* Optional HTTPS enforcement

---

## 📝 Validation

Jakarta Bean Validation is used for validating API requests.

Invalid requests return appropriate HTTP error responses through the global exception handler.

---

## 📄 API Versioning

All REST endpoints are versioned using:

```text
/api/v1/
```

This allows future API versions to be introduced without breaking existing clients.

---

## 👨‍💻 Author

**Anand Thombre**

GitHub:

https://github.com/thombreanand

---

## 📌 Repository

https://github.com/thombreanand/product-api
