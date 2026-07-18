<!-- markdownlint-disable -->

# Day 6 - Spring Boot Course API Demo

## What This Project Demonstrates

This is a small Spring Boot project that exposes a course enrollment system as REST API endpoints. It uses the same beginner-friendly course idea as the Spring Framework demo, but Spring Boot removes most manual setup.

The app starts an embedded Tomcat server, scans the project for Spring components, creates the needed objects automatically, and maps HTTP requests to controller methods.

The project focuses on these Spring Boot concepts:

| Concept | Meaning in this project |
| --- | --- |
| Spring Boot starter | `spring-boot-starter-web` brings Spring MVC, JSON support, and embedded Tomcat. |
| Auto-configuration | Spring Boot configures the web server and common Spring MVC objects automatically. |
| Embedded server | The app runs with `mvn spring-boot:run`; no external Tomcat setup is needed. |
| Component scanning | Spring finds `@RestController`, `@Service`, and `@Repository` classes automatically. |
| REST controller | `CourseController` receives HTTP requests and returns JSON responses. |
| Service layer | `EnrollmentService` applies business rules such as full courses and duplicate courses. |
| Repository layer | `CourseRepository` stores sample courses in memory while the app is running. |

## Files

| File | Purpose |
| --- | --- |
| [pom.xml](pom.xml) | Maven project file with Spring Boot web and test starters. |
| [src/main/java/com/workshop/springbootdemo/SpringBootDemoApplication.java](src/main/java/com/workshop/springbootdemo/SpringBootDemoApplication.java) | Starts the Spring Boot application. |
| [src/main/java/com/workshop/springbootdemo/controller/CourseController.java](src/main/java/com/workshop/springbootdemo/controller/CourseController.java) | Defines REST API endpoints for courses and enrollments. |
| [src/main/java/com/workshop/springbootdemo/dto/CourseRequest.java](src/main/java/com/workshop/springbootdemo/dto/CourseRequest.java) | Carries JSON data for adding a course. |
| [src/main/java/com/workshop/springbootdemo/dto/EnrollmentRequest.java](src/main/java/com/workshop/springbootdemo/dto/EnrollmentRequest.java) | Carries JSON data for enrolling a student. |
| [src/main/java/com/workshop/springbootdemo/model/Course.java](src/main/java/com/workshop/springbootdemo/model/Course.java) | Represents one course with seats and enrollment count. |
| [src/main/java/com/workshop/springbootdemo/model/EnrollmentResult.java](src/main/java/com/workshop/springbootdemo/model/EnrollmentResult.java) | Represents the result of one enrollment request. |
| [src/main/java/com/workshop/springbootdemo/repository/CourseRepository.java](src/main/java/com/workshop/springbootdemo/repository/CourseRepository.java) | Stores sample courses in memory. |
| [src/main/java/com/workshop/springbootdemo/service/EnrollmentService.java](src/main/java/com/workshop/springbootdemo/service/EnrollmentService.java) | Handles course creation and enrollment rules. |
| [src/test/java/com/workshop/springbootdemo/SpringBootDemoApplicationTests.java](src/test/java/com/workshop/springbootdemo/SpringBootDemoApplicationTests.java) | Verifies that the Spring Boot application context starts correctly. |

## Run The Project

From the repository root:

```powershell
cd .\day_6\spring_boot_demo
mvn spring-boot:run
```

The app starts at:

```text
http://localhost:8081
```

This project uses port `8081` because port `8080` is commonly used by external Tomcat during the workshop.

## Try The API

Open another terminal and try these commands.

View the welcome message:

```powershell
Invoke-RestMethod http://localhost:8081/
```

View all courses:

```powershell
Invoke-RestMethod http://localhost:8081/courses
```

Enroll a student:

```powershell
Invoke-RestMethod -Method Post -Uri http://localhost:8081/enrollments -ContentType 'application/json' -Body '{"studentName":"Riya Sharma","courseCode":"BOOT"}'
```

Add a new course:

```powershell
Invoke-RestMethod -Method Post -Uri http://localhost:8081/courses -ContentType 'application/json' -Body '{"courseCode":"REST","courseTitle":"REST API Basics","seatLimit":5}'
```

## How To Present This Demo

1. `SpringBootDemoApplication.java` starts the whole application with one `SpringApplication.run(...)` call.
2. The project does not need a separate `AppConfig` class because Spring Boot scans components automatically.
3. `CourseController` handles web requests and delegates business work to `EnrollmentService`.
4. `EnrollmentService` depends on `CourseRepository`, and Spring injects it through the constructor.
5. The REST endpoints return JSON, which is the common way backend applications talk to frontend apps and mobile apps.

## What To Try Next

After this works, try adding a delete course endpoint, search by course code, or a database-backed repository.