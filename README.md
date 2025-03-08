🚆 IRCTC Ticket Booking System

  

📌 Introduction

IRCTC Ticket Booking System is a Spring Boot-based microservices project that allows users to book and manage railway tickets. The project follows a secure authentication model using Basic Authentication and stores user and ticket data in a PostgreSQL database with JPA for ORM.

✨ Features

✔️ User authentication using Basic Auth✔️ Ticket booking and retrieval✔️ Secure storage and retrieval of user and ticket data using PostgreSQL✔️ REST API for user and ticket management

🏗️ Microservices Architecture

The system is composed of two core microservices:

🧑‍💻 User Service

Handles user authentication, registration, and session management.

| Method  | Path          | Description                | User Authenticated | Available from UI |
|---------|--------------|----------------------------|--------------------|------------------|
| POST    | `/user/signup` | Register a new user        | ❌ No               | ✅ Yes            |
| POST    | `/user/login`  | User login authentication | ✅ Yes              | ✅ Yes            |
| GET     | `/user/auth/me` | Get logged-in user info  | ✅ Yes              | ✅ Yes            |

🎫 Ticket Service

Manages ticket booking, retrieval, and storage.

| Method  | Path           | Description            | User Authenticated | Available from UI |
|---------|---------------|------------------------|--------------------|------------------|
| GET     | `/ticket/list` | Get user ticket list  | ✅ Yes              | ✅ Yes            |
| POST    | `/ticket/create` | Create a new ticket  | ✅ Yes              | ✅ Yes            |

🛠️ Infrastructure

The system is built with Spring Boot and Spring Security.

🗄️ Database

Each service has its own database to ensure separation of concerns.

PostgreSQL is used as the primary database.

Spring Data JPA is used for ORM.

🔒 Authentication

User authentication is handled via Basic Auth.

Spring Security manages secure access control.

🚀 Deployment

📋 Prerequisites

☕ Java 17+

🐘 PostgreSQL

⚙️ Maven

🐳 Docker (optional for containerization)

🔧 Build and Run

git clone https://github.com/your-repo/irctc-ticket-booking.git
cd irctc-ticket-booking

Configure PostgreSQL in application.properties.

Build the project:

mvn clean package

Run the application:

mvn spring-boot:run

📜 API Documentation

All APIs are RESTful and can be tested using tools like Postman.

🤝 Contributions

Contributions are welcome! Feel free to fork the project and submit pull requests.

⚖️ License

This project is licensed under the MIT License.

🌟 GitHub Repository

🔗 Explore the source code on GitHub:

⭐ Star the repo if you like it!

