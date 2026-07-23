<!-- markdownlint-disable -->

# Question 2 - Product CRUD Application Using Spring Data JPA

## Exact Question

Implement CRUD Operations using Spring Data JPA.

Instructions:

1. Create a Spring Boot application that performs CRUD operations on an entity using Spring Data JPA.
2. Create a new Maven project.
3. Include Spring Web, Spring Data JPA, and database dependencies in `pom.xml`.
4. Create an entity class with JPA annotations.
5. Create a repository interface using `JpaRepository` or `CrudRepository`.
6. Create service and controller classes to perform CRUD operations.
7. Test the API using Postman and handle HTTP status codes properly.

## Solution Summary

This folder contains a Spring Boot REST API named `ProductCrudApp` that performs full CRUD (Create, Read, Update, Delete) operations on a `Product` entity. It uses Spring Data JPA with the MySQL database from XAMPP (the same local server used in earlier days), a `ProductService` class that contains the business logic, and a `ProductController` that exposes the CRUD operations as REST endpoints with correct HTTP status codes and formatted exception responses.

Unlike Question 1, this project uses MySQL instead of H2, and adds a dedicated service layer between the controller and the repository, exactly as the question asks for.

## CRUD Meaning

CRUD stands for Create, Read, Update, Delete — the four basic operations any data-driven application performs on a database record.

| Operation | HTTP Method | Endpoint | Layer That Handles It |
| --- | --- | --- | --- |
| Create | `POST` | `/api/products` | `ProductController` → `ProductService` → `ProductRepository.save()` |
| Read (all) | `GET` | `/api/products` | `ProductController` → `ProductService` → `ProductRepository.findAll()` |
| Read (one) | `GET` | `/api/products/{id}` | `ProductController` → `ProductService` → `ProductRepository.findById()` |
| Update | `PUT` | `/api/products/{id}` | `ProductController` → `ProductService` → `ProductRepository.save()` |
| Delete | `DELETE` | `/api/products/{id}` | `ProductController` → `ProductService` → `ProductRepository.delete()` |

### Why a Service Class Is Needed

The question specifically separates "service and controller classes." The controller's job is only to receive the HTTP request and return the HTTP response. All business logic — like checking if a product exists before updating it, or throwing `ProductNotFoundException` — lives in [`ProductService`](ProductCrudApp/src/main/java/com/workshop/product/service/ProductService.java) instead of the controller. This keeps each class focused on one responsibility.

### JpaRepository vs CrudRepository

`CrudRepository` provides the basic save, find, and delete methods. `JpaRepository` extends `CrudRepository` and adds extra JPA-specific features such as batch operations and pagination. [`ProductRepository`](ProductCrudApp/src/main/java/com/workshop/product/repository/ProductRepository.java) extends `JpaRepository<Product, Long>`, which already includes everything `CrudRepository` offers.

### HTTP Status Codes Used

| Status Code | When It Happens |
| --- | --- |
| `201 Created` | A new product was saved successfully. |
| `200 OK` | A product was successfully retrieved, updated, or deleted. |
| `404 Not Found` | The requested product ID does not exist, handled by `GlobalExceptionHandler`. |
| `500 Internal Server Error` | Any other unexpected error, also caught and formatted by `GlobalExceptionHandler`. |

## Files

| File | Purpose |
| --- | --- |
| [ProductCrudApp/pom.xml](ProductCrudApp/pom.xml) | Maven project with Spring Web, Spring Data JPA, and MySQL dependencies. |
| [ProductCrudApp/src/main/resources/application.properties](ProductCrudApp/src/main/resources/application.properties) | Configures the server port and MySQL database connection. |
| [ProductCrudApp/src/main/java/com/workshop/product/ProductCrudAppApplication.java](ProductCrudApp/src/main/java/com/workshop/product/ProductCrudAppApplication.java) | Starts the Spring Boot application. |
| [ProductCrudApp/src/main/java/com/workshop/product/model/Product.java](ProductCrudApp/src/main/java/com/workshop/product/model/Product.java) | JPA entity representing a product. |
| [ProductCrudApp/src/main/java/com/workshop/product/repository/ProductRepository.java](ProductCrudApp/src/main/java/com/workshop/product/repository/ProductRepository.java) | Spring Data JPA repository for `Product`. |
| [ProductCrudApp/src/main/java/com/workshop/product/service/ProductService.java](ProductCrudApp/src/main/java/com/workshop/product/service/ProductService.java) | Business logic for create, read, update, and delete operations. |
| [ProductCrudApp/src/main/java/com/workshop/product/controller/ProductController.java](ProductCrudApp/src/main/java/com/workshop/product/controller/ProductController.java) | REST controller exposing the five CRUD endpoints. |
| [ProductCrudApp/src/main/java/com/workshop/product/exception/ProductNotFoundException.java](ProductCrudApp/src/main/java/com/workshop/product/exception/ProductNotFoundException.java) | Custom exception thrown when a product ID is not found. |
| [ProductCrudApp/src/main/java/com/workshop/product/exception/ApiError.java](ProductCrudApp/src/main/java/com/workshop/product/exception/ApiError.java) | Formatted error response shape (timestamp, status, message). |
| [ProductCrudApp/src/main/java/com/workshop/product/exception/GlobalExceptionHandler.java](ProductCrudApp/src/main/java/com/workshop/product/exception/GlobalExceptionHandler.java) | Catches exceptions and returns a formatted JSON error with the correct HTTP status code. |

## Database Details

| Setting | Value |
| --- | --- |
| Host | `localhost` |
| Port | `3306` |
| Database | `product_crud_app` |
| Username | `root` |
| Password | empty password |

The database is created automatically because the JDBC URL includes `createDatabaseIfNotExist=true`. The `products` table is created automatically by JPA using `spring.jpa.hibernate.ddl-auto=update`.

## How to Run

Start MySQL from XAMPP, then from the repository root:

```powershell
cd .\day_9\Question_2\ProductCrudApp
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8084
```

## How to Test in Postman

Create a new request for each of the following. Set the request body type to **raw** and **JSON** for `POST` and `PUT`.

| # | Method | URL | Body (raw JSON) | Expected Status |
| --- | --- | --- | --- | --- |
| 1 | `POST` | `http://localhost:8084/api/products` | `{"name":"Wireless Mouse","category":"Electronics","price":499.0,"quantity":50}` | `201 Created` |
| 2 | `POST` | `http://localhost:8084/api/products` | `{"name":"Mechanical Keyboard","category":"Electronics","price":1999.0,"quantity":20}` | `201 Created` |
| 3 | `GET` | `http://localhost:8084/api/products` | *(no body)* | `200 OK` — returns both products |
| 4 | `GET` | `http://localhost:8084/api/products/1` | *(no body)* | `200 OK` — returns the wireless mouse |
| 5 | `PUT` | `http://localhost:8084/api/products/1` | `{"name":"Wireless Mouse Pro","category":"Electronics","price":599.0,"quantity":45}` | `200 OK` — returns the updated product |
| 6 | `DELETE` | `http://localhost:8084/api/products/2` | *(no body)* | `200 OK` — deletion confirmation message |
| 7 | `GET` | `http://localhost:8084/api/products/2` | *(no body)* | `404 Not Found` — formatted error since it was just deleted |

Run these seven requests in order (1 through 7) so the IDs line up, screenshot each response, and that demonstrates every CRUD operation plus correct exception handling.

## Verified Output

These commands were run against the live application to confirm it works as intended:

```text
POST /api/products  -> 201 Created  -> {"id":1,"name":"Wireless Mouse", ...}
POST /api/products  -> 201 Created  -> {"id":2,"name":"Mechanical Keyboard", ...}
GET  /api/products   -> 200 OK      -> [product 1, product 2]
GET  /api/products/1 -> 200 OK      -> {"id":1,"name":"Wireless Mouse", ...}
PUT  /api/products/1 -> 200 OK      -> {"id":1,"name":"Wireless Mouse Pro", ...}
DELETE /api/products/2 -> 200 OK    -> "Product with id 2 deleted successfully."
GET  /api/products/2 -> 404 Not Found -> {"timestamp":"...","status":404,"message":"Product not found with id 2"}
```

## Likely Questions From Question 2

| Question | Short Answer |
| --- | --- |
| What does CRUD stand for? | Create, Read, Update, Delete. |
| What is `JpaRepository`? | A Spring Data interface that provides ready-made database operations for an entity. |
| Why use a service layer? | To separate business logic from the controller, keeping each class focused on one job. |
| What HTTP method is used to update a record? | `PUT`. |
| What HTTP method is used to remove a record? | `DELETE`. |
| What status code means "created successfully"? | `201 Created`. |
| What status code means "resource not found"? | `404 Not Found`. |
| How are exceptions handled here? | A custom `ProductNotFoundException` is thrown by the service and converted into a formatted JSON error by `GlobalExceptionHandler`. |
| Which database is used? | MySQL, running through XAMPP, with the database created automatically at startup. |
