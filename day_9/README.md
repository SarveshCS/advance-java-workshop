<!-- markdownlint-disable -->

# Day 9 - Assessment 3 (Attempt Any Four Questions)

This folder answers the Assessment 3 question paper. Each question lives in its own `Question_N` folder with a full working project and its own README.

## Assessment Notes

- Attempt any four questions; all questions carry equal marks.
- Every program must use proper exception handling and produce neatly formatted output.
- Any code editor can be used (VS Code, IntelliJ IDEA, Eclipse, etc.) to write and run the code.
- After finishing all attempted questions, put the source code and output screenshots of each into one PDF named `NAME_BRANCH_SECTION_ROLLNO` and submit it through MS Forms.

## Big Picture

| Question | Main Topic | Type of Project | Database |
| --- | --- | --- | --- |
| Question 1 | Spring Boot, Spring Data JPA, REST controller | Spring Boot REST API (embedded Tomcat) | H2 (embedded, in-memory) |
| Question 2 | Spring Data JPA CRUD, service layer, HTTP status codes | Spring Boot REST API (embedded Tomcat) | MySQL (XAMPP) |
| Question 3 | RESTful CRUD, `@ControllerAdvice`, custom exceptions | Spring Boot REST API (embedded Tomcat) | MySQL (XAMPP) |
| Question 4 | Spring Boot annotations, Lombok, static HTML, formatted output | Spring Boot web app (embedded Tomcat) | None (in-memory list) |

All four required questions have been attempted, satisfying the "attempt any four" rule.

## Common Terms

### Spring Initializr

Spring Initializr (`start.spring.io`) is the standard way to start a new Spring Boot project. You pick the build tool (Maven), language (Java), Spring Boot version, project metadata (group/artifact), and the starter dependencies you need. It then generates a ready `pom.xml`, a main application class, and an empty `application.properties`.

### Spring Boot Auto-Configuration

Spring Boot inspects which dependencies ("starters") are on the classpath and automatically registers the beans a typical application with those dependencies would need, without any manual `@Configuration` class. For example, adding `spring-boot-starter-data-jpa` plus a database driver is enough for Spring Boot to configure a working `DataSource` and `EntityManager` on its own.

### Spring Data JPA

A Spring module built on top of JPA (Java Persistence API) that removes the need to write DAO implementation classes. A repository interface such as `StudentRepository extends JpaRepository<Student, Long>` already has working `save()`, `findAll()`, and `findById()` methods generated at runtime.

### H2 Database

A small, embeddable, pure-Java database. It can run entirely in memory inside the same process as the Spring Boot application, so no separate database server (like XAMPP's MySQL) needs to be installed or started. Data in an in-memory H2 database is lost when the application stops, which is fine for demos and coursework.

### Lombok

A compile-time code generator. Annotations like `@Getter`, `@Setter`, `@NoArgsConstructor`, and `@AllArgsConstructor` tell Lombok to generate that boilerplate code automatically during compilation, so it does not need to be typed by hand in the entity class.

### REST Controller

A Spring MVC controller annotated with `@RestController` whose methods return data (usually converted to JSON automatically) instead of view names. Combined with `@RequestMapping`, `@GetMapping`, and `@PostMapping`, it defines the URL paths and HTTP methods an API responds to.

## Question 1 - Student Management System

See [Question_1/README.md](Question_1/README.md) for the exact question text, full explanation of Spring Initializr/auto-configuration/JPA/H2/Lombok, file list, database details, run steps, and verified output.

## Question 2 - Product CRUD Application

See [Question_2/README.md](Question_2/README.md) for the exact question text, CRUD explanation, service-layer reasoning, HTTP status code table, Postman testing steps with exact endpoints and JSON bodies, and verified output.

## Question 3 - Book Record API With Exception Handling

See [Question_3/README.md](Question_3/README.md) for the exact question text, `@ControllerAdvice` explanation, the two custom exceptions used (not-found and duplicate-ISBN), the minimal Postman testing steps, and verified output.

## Question 4 - Course Information Application

See [Question_4/README.md](Question_4/README.md) for the exact question text, static content auto-configuration explanation, Lombok annotations used, the formatted HTML output endpoint, and the browser URLs to check (no Postman needed).

## Project File and Folder Names

| Name | Meaning |
| --- | --- |
| `src/main/java` | Main Java source code folder in Maven. |
| `src/main/resources` | Configuration files copied to the classpath, such as `application.properties`. |
| `target` | Maven-generated build output, not submitted as source code. |
| `.java` | Java source file. |
| `.properties` | Key-value configuration file read by Spring Boot at startup. |

## General Viva Questions

| Question | Short Answer |
| --- | --- |
| What is Spring Boot? | A Spring project that removes manual configuration through starters and auto-configuration, and runs with an embedded server. |
| What is a starter dependency? | A single Maven dependency that pulls in every library normally needed for one feature, such as `spring-boot-starter-web`. |
| What is an embedded server? | A web server (Tomcat by default) bundled inside the application JAR, so no separate Tomcat installation is required to run it. |
| Why H2 instead of MySQL here? | The question specifically asks for the H2 database, which needs no external server and is ideal for quick, self-contained demos. |
| What should not be submitted as code? | `target/`, `.class` files, and any generated Maven build output. |
