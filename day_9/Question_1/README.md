<!-- markdownlint-disable -->

# Question 1 - Student Management System Using Spring Boot and H2

## Exact Question

Develop a Spring Boot Application with H2 Database.

Task:

1. Create a Spring Boot application named `StudentManagementSystem` using Spring Initializr. The application should demonstrate Spring Boot auto-configuration, annotations, and Spring Data JPA with H2 database.
2. Add required dependencies such as Spring Web, Spring Data JPA, H2 Database, and Project Lombok.
3. Create a `Student` entity with suitable JPA annotations.
4. Configure H2 database and application properties.
5. Create a REST controller to save and retrieve student details.
6. Handle exceptions properly and display formatted output.

## Solution Summary

This folder contains a Spring Boot REST API named `StudentManagementSystem`. It stores student records in an in-memory H2 database using Spring Data JPA, and exposes endpoints to save, list, and fetch students by ID. Lombok is used to remove boilerplate getters/setters/constructors from the entity, and a global exception handler returns a clean, formatted JSON error instead of a raw stack trace when a student is not found.

This project does not use MySQL/XAMPP. H2 is a separate lightweight database that runs embedded inside the Spring Boot application itself, which is exactly what the question asks for.

## What Spring Initializr Actually Does (Manual Steps)

Spring Initializr (`https://start.spring.io`) is a project generator. When you "manually" create a Spring Boot project through it, these are the steps and what each one produces:

| Step | What You Pick | What It Produces |
| --- | --- | --- |
| 1. Project | Maven | Generates a `pom.xml` build file instead of a Gradle file. |
| 2. Language | Java | Sets the source folder to `src/main/java`. |
| 3. Spring Boot Version | 3.3.x | Sets the `<parent>` version in `pom.xml`, which controls every other dependency's version automatically. |
| 4. Group | `com.workshop` | Becomes the base Java package name. |
| 5. Artifact | `student-management-system` | Becomes the `<artifactId>` and the default JAR file name. |
| 6. Dependencies | Spring Web, Spring Data JPA, H2 Database, Lombok | Adds the matching `<dependency>` blocks to `pom.xml`. |
| 7. Generate | — | Downloads a ready-to-run `.zip` with `pom.xml`, an empty main class, and `application.properties` already created. |

This project's [`pom.xml`](StudentManagementSystem/pom.xml) and [`StudentManagementSystemApplication.java`](StudentManagementSystem/src/main/java/com/workshop/student/StudentManagementSystemApplication.java) are exactly what Spring Initializr would generate for those choices, then filled in with the entity, repository, and controller by hand.

## Concepts Used

### Spring Boot Auto-Configuration

Spring Boot looks at the dependencies (called "starters") on the classpath and automatically configures the beans a normal app would need. For example, because `spring-boot-starter-data-jpa` and the `h2` dependency are present, Spring Boot automatically:

- Creates a `DataSource` bean pointing at the H2 database from `application.properties`.
- Creates a `JdbcTemplate`/`EntityManager` without any manual Java configuration class.
- Starts an embedded Tomcat server because `spring-boot-starter-web` is present.

This is why this project has no `AppConfig.java` like the Day 7 Spring Core examples. Spring Boot configures everything from the starter dependencies and `application.properties` instead.

### Spring Data JPA

JPA (Java Persistence API) is a specification for mapping Java classes to database tables. Spring Data JPA is Spring's library that implements JPA repositories automatically.

In this project, [`StudentRepository`](StudentManagementSystem/src/main/java/com/workshop/student/repository/StudentRepository.java) is just an empty interface:

```java
public interface StudentRepository extends JpaRepository<Student, Long> {
}
```

By extending `JpaRepository<Student, Long>`, Spring generates a working implementation at runtime with methods like `save()`, `findAll()`, and `findById()` — no SQL and no implementation class needs to be written.

### H2 Database

H2 is a small Java-based database that can run fully in memory, inside the same process as the Spring Boot application. It needs no separate server, no XAMPP, and no install step, which is why the question asks for it instead of MySQL. Data in this project's H2 database (`jdbc:h2:mem:studentdb`) is created fresh every time the application starts and is lost when it stops.

H2 also ships a web-based console for browsing tables, enabled here through `spring.h2.console.enabled=true` at `http://localhost:8083/h2-console`.

### JPA Annotations Used

| Annotation | Meaning |
| --- | --- |
| `@Entity` | Marks the `Student` class as a JPA entity mapped to a database table. |
| `@Table(name = "students")` | Sets the exact table name. |
| `@Id` | Marks the primary key field. |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | Lets the database auto-increment the ID column. |
| `@Column(nullable = false)` | Marks a column as required. |
| `@Column(unique = true)` | Ensures no two students share the same roll number. |

### Lombok Annotations Used

| Annotation | Meaning |
| --- | --- |
| `@Getter` | Generates a getter method for every field. |
| `@Setter` | Generates a setter method for every field. |
| `@NoArgsConstructor` | Generates an empty constructor, required by JPA. |
| `@AllArgsConstructor` | Generates a constructor with every field as a parameter. |

Lombok needs an explicit `annotationProcessorPaths` entry for the Maven compiler plugin (see [`pom.xml`](StudentManagementSystem/pom.xml)) so Maven actually runs Lombok's code generator during compilation instead of silently skipping it.

### REST Controller Annotations Used

| Annotation | Meaning |
| --- | --- |
| `@RestController` | Marks the class as a controller that returns JSON/data directly instead of view names. |
| `@RequestMapping("/api/students")` | Sets the base URL path for every endpoint in the class. |
| `@PostMapping` | Handles `POST` requests, used here to save a student. |
| `@GetMapping` | Handles `GET` requests, used here to retrieve students. |
| `@PathVariable` | Reads a value from the URL path, such as `{id}`. |
| `@RequestBody` | Converts incoming JSON into a `Student` object. |

### Exception Handling

[`StudentNotFoundException`](StudentManagementSystem/src/main/java/com/workshop/student/exception/StudentNotFoundException.java) is thrown when a requested student ID does not exist. [`GlobalExceptionHandler`](StudentManagementSystem/src/main/java/com/workshop/student/exception/GlobalExceptionHandler.java) uses `@RestControllerAdvice` to catch it application-wide and return a clean, formatted JSON error body with a timestamp, HTTP status, and message, instead of the default HTML error page or a raw stack trace.

## Files

| File | Purpose |
| --- | --- |
| [StudentManagementSystem/pom.xml](StudentManagementSystem/pom.xml) | Maven project with Spring Web, Spring Data JPA, H2, and Lombok dependencies. |
| [StudentManagementSystem/src/main/resources/application.properties](StudentManagementSystem/src/main/resources/application.properties) | Configures the server port and the H2 database connection. |
| [StudentManagementSystem/src/main/java/com/workshop/student/StudentManagementSystemApplication.java](StudentManagementSystem/src/main/java/com/workshop/student/StudentManagementSystemApplication.java) | Starts the Spring Boot application. |
| [StudentManagementSystem/src/main/java/com/workshop/student/model/Student.java](StudentManagementSystem/src/main/java/com/workshop/student/model/Student.java) | JPA entity for student records, using Lombok for getters/setters/constructors. |
| [StudentManagementSystem/src/main/java/com/workshop/student/repository/StudentRepository.java](StudentManagementSystem/src/main/java/com/workshop/student/repository/StudentRepository.java) | Spring Data JPA repository providing save/find operations for `Student`. |
| [StudentManagementSystem/src/main/java/com/workshop/student/controller/StudentController.java](StudentManagementSystem/src/main/java/com/workshop/student/controller/StudentController.java) | REST controller exposing save, list, and find-by-id endpoints. |
| [StudentManagementSystem/src/main/java/com/workshop/student/exception/StudentNotFoundException.java](StudentManagementSystem/src/main/java/com/workshop/student/exception/StudentNotFoundException.java) | Custom exception thrown when a student ID is not found. |
| [StudentManagementSystem/src/main/java/com/workshop/student/exception/ApiError.java](StudentManagementSystem/src/main/java/com/workshop/student/exception/ApiError.java) | Formatted error response shape (timestamp, status, message). |
| [StudentManagementSystem/src/main/java/com/workshop/student/exception/GlobalExceptionHandler.java](StudentManagementSystem/src/main/java/com/workshop/student/exception/GlobalExceptionHandler.java) | Catches exceptions application-wide and returns a formatted JSON error. |

## Database Details

| Setting | Value |
| --- | --- |
| Type | H2 (embedded, in-memory) |
| JDBC URL | `jdbc:h2:mem:studentdb` |
| Username | `sa` |
| Password | empty password |
| Console | `http://localhost:8083/h2-console` |

No XAMPP/MySQL setup is needed for this question, because H2 runs inside the application itself.

## How to Run

From the repository root:

```powershell
cd .\day_9\Question_1\StudentManagementSystem
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8083
```

## API Endpoints

| Method | URL | Purpose |
| --- | --- | --- |
| `POST` | `/api/students` | Saves a new student. Body: `{"name":"...","rollNumber":"...","department":"...","marks":...}` |
| `GET` | `/api/students` | Retrieves the list of all students. |
| `GET` | `/api/students/{id}` | Retrieves one student by ID, or a `404` formatted error if not found. |

## Verified Output

These commands were run against the live application to confirm it works as intended:

```powershell
curl.exe -X POST http://localhost:8083/api/students -H "Content-Type: application/json" -d "@student1.json"
# {"id":1,"name":"Aarav Sharma","rollNumber":"CS101","department":"Computer Science","marks":88.5}
# HTTP 201 Created

curl.exe http://localhost:8083/api/students
# [{"id":1,"name":"Aarav Sharma", ...}, {"id":2,"name":"Ananya Gupta", ...}]
# HTTP 200 OK

curl.exe http://localhost:8083/api/students/1
# {"id":1,"name":"Aarav Sharma","rollNumber":"CS101","department":"Computer Science","marks":88.5}
# HTTP 200 OK

curl.exe http://localhost:8083/api/students/99
# {"timestamp":"2026-07-23T14:17:48.968","status":404,"message":"Student not found with id 99"}
# HTTP 404 Not Found
```

To see the same result visually for the report, open these two pages in a browser while the application is running and take a screenshot of each:

```text
http://localhost:8083/api/students
http://localhost:8083/h2-console
```

## Likely Questions From Question 1

| Question | Short Answer |
| --- | --- |
| What is Spring Initializr? | A web tool that generates a ready-to-run Spring Boot project skeleton from chosen dependencies. |
| What is auto-configuration? | Spring Boot automatically configures beans based on the dependencies found on the classpath. |
| What is Spring Data JPA? | A Spring module that generates repository implementations for JPA entities automatically. |
| What is H2? | A lightweight, embeddable Java database, often used in-memory for development and testing. |
| Why use Lombok? | To remove repetitive getter, setter, and constructor code from POJOs and entities. |
| What does `@RestController` do? | Marks a class whose methods return data (JSON) directly instead of view names. |
| Why use `@RestControllerAdvice`? | To handle exceptions from every controller in one place and return a consistent error format. |
| What happens if a student ID does not exist? | `StudentNotFoundException` is thrown and converted into a `404` JSON error by `GlobalExceptionHandler`. |
| Is this a web project? | Yes, it is a Spring Boot REST API with an embedded Tomcat server. |
