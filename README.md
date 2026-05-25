# CoreBank — Digital Banking Platform

A full-stack employee-facing banking platform built as a team project for 
CPRO 2221 (Java Enterprise Programming) at Red Deer Polytechnic.

**Team:** Aman Vhora, Jay Sukhadia, Hitanshu Bhatt

---

## Tech Stack
- Java 21 + Spring Boot 3
- SQL Server (SSMS)
- Thymeleaf (front-end templates)
- JWT Authentication + Spring Security
- Maven + Lombok

---

## Features
- Employee login with JWT authentication and BCrypt password hashing
- Account creation, deposits, withdrawals, and account management
- Fund transfers between accounts
- Loan application and approval/denial based on credit score
- Credit card application system
- Fraud reporting and alert management
- Bill payments (one-time and scheduled)
- Transaction history with filters
- Personalized budgeting tools and spending insights

---

## Setup
1. Install Java 21, Maven, SQL Server, IntelliJ IDEA
2. Create a database named `CoreBankDB` in SSMS
3. Edit `src/main/resources/application.properties`:

spring.datasource.url=jdbc:sqlserver://localhost;databaseName=CoreBankDB
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
app.jwt.secret=your_secret_key

4. Run: `mvn spring-boot:run`

---

## API Endpoints
- `POST /api/auth/login` — employee login, returns JWT
- `GET /api/accounts` — list all accounts
- `POST /api/transfers` — transfer funds
- `GET /api/transactions` — transaction history
- `POST /api/bill-payments` — schedule bill payment
- `GET /api/budgets/insights` — spending vs budget limits

Use `Authorization: Bearer <token>` header for all protected routes.

---

## Notes
Built and tested using Postman. 
This was my first full-stack Java enterprise project — 
learned a lot debugging Spring Security and JWT filter chains.
