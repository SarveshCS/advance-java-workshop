<!-- markdownlint-disable -->

# Question 4 - Course Information Application Using Spring Boot and Lombok

## Exact Question

Create a Course Information Application using Spring Boot and Lombok.

Create a Spring Boot application named `CourseInfoApp` demonstrating Spring Boot annotations, Project Lombok, and static HTML content handling.

Steps to Implement:

1. Create a Spring Boot project using Spring Initializr.
2. Include Spring Web and Project Lombok dependencies.
3. Create a `Course` class using Lombok annotations.
4. Create a controller to handle HTTP methods and serve HTML/static pages.
5. Display course details in a formatted output.
6. Ensure proper use of Spring Boot annotations and auto-configuration.

## Solution Summary

This folder contains a Spring Boot application named `CourseInfoApp`. It has no database — course data is a small in-memory list — which matches the question's dependency list of only Spring Web and Lombok. It demonstrates three things the question specifically asks for: Lombok-based entity code, a controller returning both JSON and hand-built formatted HTML, and Spring Boot's automatic static file serving.

## Static HTML Content Handling (Auto-Configuration)

Spring Boot automatically serves any file placed in `src/main/resources/static` at the root URL, with **no controller code required**. This project's [`static/index.html`](CourseInfoApp/src/main/resources/static/index.html) is served automatically at:

```text
http://localhost:8086/
```

This is a direct demonstration of auto-configuration: because `spring-boot-starter-web` is on the classpath, Spring Boot configures a static resource handler for the `static/`, `public/`, and `resources/` classpath folders on its own.

## Course Class With Lombok

[`Course.java`](CourseInfoApp/src/main/java/com/workshop/course/model/Course.java) uses Lombok annotations instead of hand-written getters, setters, and constructors:

| Annotation | Meaning |
| --- | --- |
| `@Getter` | Generates a getter for every field. |
| `@Setter` | Generates a setter for every field. |
| `@NoArgsConstructor` | Generates an empty constructor. |
| `@AllArgsConstructor` | Generates a constructor with all fields. |
| `@ToString` | Generates a readable `toString()` method for logging/printing. |

As with Question 1, Lombok needs the `annotationProcessorPaths` entry in the Maven compiler plugin (see [`pom.xml`](CourseInfoApp/pom.xml)) or it silently produces a class with no generated methods.

## Controller Handling HTTP Methods and Formatted Output

[`CourseController.java`](CourseInfoApp/src/main/java/com/workshop/course/controller/CourseController.java) exposes three `GET` endpoints:

| Endpoint | Returns | Purpose |
| --- | --- | --- |
| `/api/courses` | JSON array | List every course. |
| `/api/courses/{id}` | JSON object | One course's raw data. |
| `/api/courses/{id}/view` | HTML table | The same course's details in a neatly formatted, human-readable HTML page — this is the "display course details in a formatted output" requirement. |

The `/view` endpoint sets `produces = MediaType.TEXT_HTML_VALUE` so the browser renders the returned string as an HTML table instead of showing it as plain text.

## Spring Boot Annotations Used

| Annotation | Meaning |
| --- | --- |
| `@SpringBootApplication` | Combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan` to bootstrap the app. |
| `@RestController` | Marks the controller so its methods return data/HTML directly instead of view names. |
| `@RequestMapping("/api/courses")` | Sets the base path for the controller. |
| `@GetMapping` | Maps a method to a `GET` request. |
| `@PathVariable` | Reads the `{id}` value from the URL. |
| `@Service` | Marks `CourseService` as a Spring-managed business logic bean. |
| `@RestControllerAdvice` / `@ExceptionHandler` | Handles `CourseNotFoundException` application-wide and returns a formatted `404` JSON error. |

## Files

| File | Purpose |
| --- | --- |
| [CourseInfoApp/pom.xml](CourseInfoApp/pom.xml) | Maven project with Spring Web and Lombok dependencies. |
| [CourseInfoApp/src/main/resources/application.properties](CourseInfoApp/src/main/resources/application.properties) | Sets the server port. |
| [CourseInfoApp/src/main/resources/static/index.html](CourseInfoApp/src/main/resources/static/index.html) | Static HTML page served automatically by Spring Boot, with links to every endpoint. |
| [CourseInfoApp/src/main/java/com/workshop/course/CourseInfoAppApplication.java](CourseInfoApp/src/main/java/com/workshop/course/CourseInfoAppApplication.java) | Starts the Spring Boot application. |
| [CourseInfoApp/src/main/java/com/workshop/course/model/Course.java](CourseInfoApp/src/main/java/com/workshop/course/model/Course.java) | Course data class built entirely with Lombok annotations. |
| [CourseInfoApp/src/main/java/com/workshop/course/service/CourseService.java](CourseInfoApp/src/main/java/com/workshop/course/service/CourseService.java) | Holds the in-memory list of sample courses and looks one up by ID. |
| [CourseInfoApp/src/main/java/com/workshop/course/controller/CourseController.java](CourseInfoApp/src/main/java/com/workshop/course/controller/CourseController.java) | Serves course data as JSON and as a formatted HTML table. |
| [CourseInfoApp/src/main/java/com/workshop/course/exception/CourseNotFoundException.java](CourseInfoApp/src/main/java/com/workshop/course/exception/CourseNotFoundException.java) | Custom exception thrown when a course ID does not exist. |
| [CourseInfoApp/src/main/java/com/workshop/course/exception/ApiError.java](CourseInfoApp/src/main/java/com/workshop/course/exception/ApiError.java) | Formatted error response shape (timestamp, status, message). |
| [CourseInfoApp/src/main/java/com/workshop/course/exception/GlobalExceptionHandler.java](CourseInfoApp/src/main/java/com/workshop/course/exception/GlobalExceptionHandler.java) | Converts `CourseNotFoundException` into a formatted `404` JSON response. |

## How to Run

No database is required for this question. From the repository root:

```powershell
cd .\day_9\Question_4\CourseInfoApp
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8086
```

## What to Check in the Browser (No Postman Needed)

Every endpoint here is a simple `GET`, so a browser is enough — open each URL directly:

| # | URL | What You Should See |
| --- | --- | --- |
| 1 | `http://localhost:8086/` | The static `index.html` welcome page with links (proves static content auto-configuration). |
| 2 | `http://localhost:8086/api/courses` | A JSON array of three sample courses. |
| 3 | `http://localhost:8086/api/courses/1` | A single course as JSON. |
| 4 | `http://localhost:8086/api/courses/1/view` | The same course rendered as a formatted HTML table. |
| 5 | `http://localhost:8086/api/courses/99` | A formatted `404` JSON error, since course ID `99` does not exist. |

Screenshot these 5 pages — that alone covers every task item in the question (Lombok class, controller, static page, formatted output, and correct auto-configuration behavior).

## Likely Questions From Question 4

| Question | Short Answer |
| --- | --- |
| Where does Spring Boot look for static files? | `src/main/resources/static`, `public`, or `resources` on the classpath. |
| Do you need a controller to serve `index.html`? | No, Spring Boot's auto-configuration serves it automatically. |
| What does `@ToString` do? | Lombok generates a `toString()` method that prints all field values. |
| Why does the `/view` endpoint set `produces = MediaType.TEXT_HTML_VALUE`? | So the browser renders the returned string as HTML instead of plain text. |
| What is `@SpringBootApplication`? | A combination of `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`. |
| Is a database used in this project? | No, course data is stored in a small in-memory list inside `CourseService`. |
