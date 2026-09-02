# Product REST API – Spring Boot

A production-style RESTful Product CRUD API using Java 17, Spring Boot, Spring Data JPA/Hibernate, MySQL, Spring Security JWT + refresh-token rotation, OpenAPI/Swagger, JUnit 5, Mockito, H2 and Docker Compose.

## Architecture

`Controller -> Service -> Repository -> Database`

- **Controller**: REST endpoints, request validation and HTTP status codes.
- **Service**: business rules, audit fields and transaction boundaries.
- **Repository**: Spring Data JPA persistence.
- **Security**: stateless JWT access tokens; opaque refresh tokens are stored as SHA-256 hashes and rotated on use.
- **Exception handler**: one JSON error shape for validation and business errors.
- **Database indexes**: product name/created time and item/product foreign key are indexed.

## Requirements

- Java 17+
- Maven 3.9+
- Docker Desktop (recommended)

## Run with Docker

```bash
docker compose up --build
```

API: `http://localhost:8080`
Swagger UI: `http://localhost:8080/swagger-ui.html`

## Run locally

Start MySQL and create `productdb`, then:

```bash
mvn spring-boot:run
```

Override credentials with environment variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
JWT_ACCESS_EXPIRATION_SECONDS
```

For production, **never use the development JWT secret** and store secrets in a secret manager/environment configuration.

## Authentication

1. `POST /api/v1/auth/register`
2. `POST /api/v1/auth/login`
3. Use the returned access token as `Authorization: Bearer <token>`.
4. `POST /api/v1/auth/refresh` rotates the refresh token. The previous refresh token is revoked.

The seeded application does not create an admin automatically. For an assignment demo, register a user and change its role in the database to `ADMIN`, or add a controlled migration/seeder before production deployment.

## API

### Auth
- `POST /api/v1/auth/register`
- `POST /api/v1/auth/login`
- `POST /api/v1/auth/refresh`

### Products
- `GET /api/v1/products?page=0&size=20&sort=id,asc`
- `GET /api/v1/products/{id}`
- `POST /api/v1/products` — ADMIN
- `PUT /api/v1/products/{id}` — ADMIN
- `DELETE /api/v1/products/{id}` — ADMIN
- `GET /api/v1/products/{id}/items`

Example product request:

```json
{
  "productName": "Laptop",
  "items": [
    { "quantity": 10 },
    { "quantity": 5 }
  ]
}
```

## Testing

```bash
mvn test
```

Unit tests use JUnit 5 + Mockito. Integration context tests use Spring Boot Test and H2 under the `test` profile.

## HTTPS and CORS

CORS is configurable through the application configuration/environment. For production deployment, terminate TLS at a reverse proxy/load balancer (for example Nginx or a cloud load balancer), redirect HTTP to HTTPS there, and forward only HTTPS traffic to the application. Do not expose the development HTTP endpoint directly to the public internet.

## GitHub submission

Create a **public** GitHub repository, commit the source, README, Dockerfile and docker-compose.yml, then submit only the repository URL in the Google Form. Do not submit a ZIP file.
