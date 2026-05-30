# Creator Store Backend

A Java-based REST API for an e-commerce Creator Store, built with Spring Boot. This backend service manages the core product catalog and handles the order processing lifecycle.

## 🏗️ Project Structure
This project follows a standard, scalable layered architecture:

* **Controllers (`/controllers`)**: Exposes RESTful endpoints for `OrderController` and `ProductController`.
* **Services (`/services`)**: Contains the core business logic (`OrderService`, `ProductService`).
* **Repositories (`/repositories`)**: Interfaces for the data access layer handling `Order`, `OrderItem`, and `Product` entities.
* **Entities (`/entities`)**: Database models representing the core domain.
* **DTOs (`/dto`)**: Data Transfer Objects (`OrderRequest`, `OrderItemRequest`) used to structure incoming API payloads securely.

## 💻 Tech Stack
* **Language:** Java
* **Framework:** Spring Boot
* **Database:** PostgreSQL *(Configured via `application.yaml`)*
* **Build Tool:** Maven

## 🚀 #100DaysOfCode
*Documenting my backend development journey and daily progress.*
