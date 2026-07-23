<!-- markdownlint-disable -->

# Question 3 - Book Record API With Exception Handling

## Exact Question

Develop a RESTful API with Exception Handling.

Instructions:

1. Create a Spring Boot REST API for managing records and implement proper exception handling.
2. Create a new Spring Boot project.
3. Create entity, repository, service, and controller layers.
4. Use RESTful endpoints for create, read, update, and delete operations.
5. Implement exception handling using `@ControllerAdvice` and custom exceptions.
6. Return meaningful HTTP status codes for success and error responses.
7. Verify the API response using Postman.

## Solution Summary

This folder contains a Spring Boot REST API named `BookRecordApi` that manages `Book` records with full CRUD operations, backed by MySQL through Spring Data JPA. It follows the same entity → repository → service → controller layering as Question 2, but focuses on the exception-handling requirement by using **two** custom exceptions handled through `@ControllerAdvice`, each mapped to a different, meaningful HTTP status code.

## Two Kinds of Exceptions Handled

| Exception | When It Is Thrown | HTTP Status Returned |
| --- | --- | --- |
| `BookNotFoundException` | A requested book ID does not exist (`GET`, `PUT`, `DELETE` by ID). | `404 Not Found` |
| `DuplicateIsbnException` | A new book is created with an ISBN that already exists. | `409 Conflict` |
| Any other unexpected exception | Anything not explicitly handled above. | `500 Internal Server Error` |

### Why `@ControllerAdvice` Instead of Try-Catch in the Controller

Without `@ControllerAdvice`, every controller method would need its own `try`/`catch` block to convert an exception into a proper HTTP response, repeating the same error-formatting code everywhere. [`GlobalExceptionHandler`](BookRecordApi/src/main/java/com/workshop/book/exception/GlobalExceptionHandler.java) is annotated with `@ControllerAdvice`, so Spring automatically routes any exception thrown from any controller in the application to the matching `@ExceptionHandler` method here — one place handles errors for the whole API.

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleBookNotFound(BookNotFoundException exception) {
        ...
    }
}
```

`@ResponseBody` is needed alongside plain `@ControllerAdvice` (unlike `@RestControllerAdvice`, which already implies it) so that the returned `ApiError` object is written directly as JSON instead of being treated as a view name.

## RESTful Endpoints

| Operation | HTTP Method | Endpoint | Success Status | Failure Status |
| --- | --- | --- | --- | --- |
| Create | `POST` | `/api/books` | `201 Created` | `409 Conflict` (duplicate ISBN) |
| Read all | `GET` | `/api/books` | `200 OK` | — |
| Read one | `GET` | `/api/books/{id}` | `200 OK` | `404 Not Found` |
| Update | `PUT` | `/api/books/{id}` | `200 OK` | `404 Not Found` |
| Delete | `DELETE` | `/api/books/{id}` | `200 OK` | `404 Not Found` |

## Files

| File | Purpose |
| --- | --- |
| [BookRecordApi/pom.xml](BookRecordApi/pom.xml) | Maven project with Spring Web, Spring Data JPA, and MySQL dependencies. |
| [BookRecordApi/src/main/resources/application.properties](BookRecordApi/src/main/resources/application.properties) | Configures the server port and MySQL database connection. |
| [BookRecordApi/src/main/java/com/workshop/book/BookRecordApiApplication.java](BookRecordApi/src/main/java/com/workshop/book/BookRecordApiApplication.java) | Starts the Spring Boot application. |
| [BookRecordApi/src/main/java/com/workshop/book/model/Book.java](BookRecordApi/src/main/java/com/workshop/book/model/Book.java) | JPA entity representing a book record. |
| [BookRecordApi/src/main/java/com/workshop/book/repository/BookRepository.java](BookRecordApi/src/main/java/com/workshop/book/repository/BookRepository.java) | Spring Data JPA repository, including a lookup by ISBN. |
| [BookRecordApi/src/main/java/com/workshop/book/service/BookService.java](BookRecordApi/src/main/java/com/workshop/book/service/BookService.java) | Business logic for CRUD operations and duplicate ISBN checking. |
| [BookRecordApi/src/main/java/com/workshop/book/controller/BookController.java](BookRecordApi/src/main/java/com/workshop/book/controller/BookController.java) | REST controller exposing the CRUD endpoints. |
| [BookRecordApi/src/main/java/com/workshop/book/exception/BookNotFoundException.java](BookRecordApi/src/main/java/com/workshop/book/exception/BookNotFoundException.java) | Custom exception thrown when a book ID is not found. |
| [BookRecordApi/src/main/java/com/workshop/book/exception/DuplicateIsbnException.java](BookRecordApi/src/main/java/com/workshop/book/exception/DuplicateIsbnException.java) | Custom exception thrown when a book with the same ISBN already exists. |
| [BookRecordApi/src/main/java/com/workshop/book/exception/ApiError.java](BookRecordApi/src/main/java/com/workshop/book/exception/ApiError.java) | Formatted error response shape (timestamp, status, message). |
| [BookRecordApi/src/main/java/com/workshop/book/exception/GlobalExceptionHandler.java](BookRecordApi/src/main/java/com/workshop/book/exception/GlobalExceptionHandler.java) | Uses `@ControllerAdvice` to catch both custom exceptions and return meaningful HTTP status codes. |

## Database Details

| Setting | Value |
| --- | --- |
| Host | `localhost` |
| Port | `3306` |
| Database | `book_record_api` |
| Username | `root` |
| Password | empty password |

## How to Run

Start MySQL from XAMPP, then from the repository root:

```powershell
cd .\day_9\Question_3\BookRecordApi
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8085
```

## How to Test in Postman (Minimal Essential Set)

Run these in order. Set the body type to **raw** → **JSON** for `POST` and `PUT`.

| # | Method | URL | Body (raw JSON) | Expected Status |
| --- | --- | --- | --- | --- |
| 1 | `POST` | `http://localhost:8085/api/books` | `{"title":"Clean Code","author":"Robert Martin","isbn":"9780132350884","price":650.0}` | `201 Created` (id = 1) |
| 2 | `PUT` | `http://localhost:8085/api/books/1` | `{"title":"Clean Code Second Edition","author":"Robert Martin","isbn":"9780132350884","price":750.0}` | `200 OK` — updated book |
| 3 | `DELETE` | `http://localhost:8085/api/books/1` | *(no body)* | `200 OK` — deletion message |
| 4 | `GET` | `http://localhost:8085/api/books/1` | *(no body)* | `404 Not Found` — proves `BookNotFoundException` handling |
| 5 | `POST` | `http://localhost:8085/api/books` | `{"title":"Clean Code","author":"Robert Martin","isbn":"9780132350884","price":650.0}` | `201 Created` (id = 2) |
| 6 | `POST` | `http://localhost:8085/api/books` | `{"title":"Clean Code","author":"Robert Martin","isbn":"9780132350884","price":650.0}` | `409 Conflict` — proves `DuplicateIsbnException` handling |

This set of six requests demonstrates Create, Update, Delete, a "not found" error, and a "conflict" error — every layer and both custom exceptions the question asks for, without any redundant repeat requests.

## Verified Output

```text
POST   /api/books    -> 201 Created -> {"id":1,"title":"Clean Code", ...}
GET    /api/books    -> 200 OK      -> [book 1]
PUT    /api/books/1  -> 200 OK      -> {"id":1,"title":"Clean Code Second Edition", ...}
DELETE /api/books/1  -> 200 OK      -> "Book with id 1 deleted successfully."
GET    /api/books/1  -> 404 Not Found -> {"status":404,"message":"Book not found with id 1"}
POST   /api/books    -> 201 Created -> {"id":2,"title":"Clean Code", ...}
POST   /api/books    -> 409 Conflict -> {"status":409,"message":"A book with ISBN 9780132350884 already exists."}
```

## Likely Questions From Question 3

| Question | Short Answer |
| --- | --- |
| What is `@ControllerAdvice`? | An annotation that lets one class handle exceptions thrown from any controller in the application. |
| Difference between `@ControllerAdvice` and `@RestControllerAdvice`? | `@RestControllerAdvice` already includes `@ResponseBody` on every method; plain `@ControllerAdvice` needs `@ResponseBody` added manually to return JSON. |
| What is a custom exception? | A user-defined class extending `RuntimeException` (or `Exception`) used to represent a specific application error. |
| What status code means a conflict with existing data? | `409 Conflict`. |
| What status code means the resource does not exist? | `404 Not Found`. |
| Why check for a duplicate ISBN in the service layer? | Business rules like uniqueness checks belong in the service layer, not the controller. |
| What does `@ExceptionHandler` do? | Marks a method as the handler for a specific exception type. |
