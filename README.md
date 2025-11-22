# Milhas Backend (complete scaffold)

This archive contains a complete Spring Boot backend scaffold for the "Milhas" project:
- Spring Boot 3
- Spring Security (JWT)
- JPA/Hibernate + PostgreSQL
- Flyway migrations
- File upload support (PDF/PNG/JPG)
- Docker + docker-compose

To run:
1. mvn clean package -DskipTests
2. docker-compose up --build

Default JWT secret is set in docker-compose environment — change it in production!
