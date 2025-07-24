# ProductService - Practice Project

This is a microservice built using **Spring Boot** that handles product-related operations like fetching, adding, and filtering products. It integrates with [FakeStore API](https://fakestoreapi.com/) for mock data and is part of a microservice ecosystem alongside `OrderService`, `UserService`, and `PaymentService`.

---

## 📦 Features

- Fetch all products from FakeStore API.
- Get product by ID.
- Add a new product (either to FakeStore or local DB).
- Get products by category.
- Projected queries using Spring Data JPA.
- Uses DTOs and custom exception handling.
- Supports two service implementations:
    - `FakeStoreProductService`: Uses external API (default).
    - `SelfProductService`: Persists data to a local MySQL DB.

---

## 🧱 Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- RestTemplate (for API integration)
- Hibernate
- Lombok (optional)
- Docker (optional for deployment)

---

## 🗂️ Project Structure

```plaintext
com.scaler.practiceprojectjan25
│
├── Configs             # Bean configurations (e.g., RestTemplate)
├── Controllers         # REST Controllers (e.g., ProductController)
├── Dtos                # Data Transfer Objects
├── Exceptions          # Custom exceptions
├── Models              # JPA entity models
├── Projections         # JPA interface-based projections
├── Repositories        # Spring Data JPA Repositories
├── Services            # Service interfaces and implementations
└── application.properties / .yml  # Config files
```