# 💰 Smart Expense Management System (Spring Boot)

A full-stack **Spring Boot** application for managing corporate team expenses securely with **JWT authentication**, **Spring Security**, **Spring AOP**, **Spring Cache**, and **email-based OTP verification**.

---

## 🚀 Features

- 🔐 **User Authentication with JWT**
  - Register, verify via OTP (email), and log in securely.
- 💸 **Expense Management**
  - Add, update, delete, and view expenses for authenticated users.
- ⚙️ **Spring AOP**
  - Logs service execution time and monitors performance.
- 🧠 **Spring Cache**
  - Improves performance by caching frequently accessed expenses.
- 📧 **Email OTP Verification**
  - Send OTPs to verify user registration using JavaMailSender.
- 🧱 **Spring Data JPA**
  - CRUD operations with relational database (MySQL/PostgreSQL).
- 🧰 **RESTful API**
  - Clean JSON-based APIs ready for front-end integration (React, Angular, etc.)
- 🔒 **Spring Security**
  - Secures endpoints with role-based access control.

---

## 🧩 Tech Stack

| Layer | Technology |
|--------|-------------|
| Backend | Spring Boot 3+, Spring MVC |
| Security | Spring Security + JWT |
| Caching | Spring Cache (Caffeine / Ehcache) |
| Persistence | Spring Data JPA + Hibernate |
| Database | MySQL / PostgreSQL |
| Mail | JavaMailSender (SMTP) |
| Monitoring | Spring AOP (logging aspect) |
| Build Tool | Maven |
| Language | Java 17+ |

---

## 📂 Project Structure

src/main/java/com/smartexpense/
│
├── controller/
│ ├── AuthController.java
│ └── ExpenseController.java
│
├── dto/
│ ├── RegisterRequest.java
│ ├── LoginRequest.java
│ ├── ExpenseDTO.java
│ └── OtpVerificationRequest.java
│
├── entity/
│ ├── User.java
│ └── Expense.java
│
├── service/
│ ├── AuthService.java
│ ├── ExpenseService.java
│ └── MailService.java
│
├── security/
│ ├── JwtService.java
│ └── SecurityConfig.java
│
├── aspect/
│ └── LoggingAspect.java
│
└── SmartExpenseApplication.java


🧪 Testing with Postman

Import endpoints manually or use:

POST /api/auth/register

POST /api/auth/verify-otp

POST /api/auth/login

Add JWT token in header:

Authorization: Bearer <your-token>


GET/POST/PUT/DELETE on /api/expenses


🧾 License

This project is open-source and available under the MIT License.

👨‍💻 Author

Shubham Gupta
💼 Assistant Software Engineer — Montran India
🔗 LinkedIn[https://www.linkedin.com/in/shubham-gupta-246426201/]

📧 shubhamrg30@example.com