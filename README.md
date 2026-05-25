# CoreBank (Spring Boot + MySQL)

Implements the **exact features** from the project proposal: Authentication (JWT), Account Management,
Fund Transfers, Transaction History, Bill Payments, Automated Alerts & Notifications, and Personalized Budgeting Tools.

## Prerequisites
- Java 17
- Maven 3.9+
- MySQL (create DB `corebank`)
- IntelliJ IDEA (recommended)

## Configure DB & JWT
Edit `src/main/resources/application.properties`:
```
spring.datasource.url=jdbc:mysql://localhost:3306/corebank?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_USER
spring.datasource.password=YOUR_PASSWORD
app.jwt.secret=change_me_to_a_long_random_secret
```

## Run
```bash
mvn spring-boot:run
```
Import into IntelliJ if you prefer.

## Seed Data
On first run, `data.sql` seeds:
- Users: admin, jay, aman, hitanshu (password: `password` – for demo only)
- Accounts with balances
- Sample transactions and payees

## API (brief)
- `POST /api/auth/register` – create user
- `POST /api/auth/login` – obtain JWT
- `GET  /api/accounts` – list accounts (for current user)
- `GET  /api/accounts/{id}` – account details
- `POST /api/transfers` – transfer between accounts
- `GET  /api/transactions` – history with filters
- `POST /api/payees` – add payee
- `POST /api/bill-payments` – create payment (one-time or scheduled)
- `GET  /api/alerts/prefs` – view alert preferences
- `PUT  /api/alerts/prefs` – update alert preferences
- `POST /api/alerts/evaluate` – simulate evaluation & dispatch
- `GET  /api/budgets/insights` – spending vs limits

Use the `Authorization: Bearer <token>` header (except for register/login).

## Testing
- Use Postman to exercise endpoints.
- Add JUnit tests under `src/test/java` (starter dependencies included).
