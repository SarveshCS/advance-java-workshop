<!-- markdownlint-disable -->

# Day 6 - Spring Framework Course Enrollment Project

## What This Project Demonstrates

This is a small Spring Framework Core project. It presents an interactive menu-driven course enrollment system where students are enrolled into workshop courses and a report shows seat usage.

The example is intentionally simple, but it is more meaningful than a greeting program because it has separate model, repository, service, and reporting layers. Spring creates these objects and connects them through constructor-based dependency injection. The data is stored in memory while the program is running, so no database connection is needed.

The project focuses on these beginner concepts:

| Concept | Meaning in this project |
| --- | --- |
| Spring container | The object manager created by `AnnotationConfigApplicationContext`. |
| Bean | A Java object created and managed by Spring. |
| Dependency injection | Spring passes `CourseRepository` into `EnrollmentService`, then passes `EnrollmentService` into `ReportService`. |
| Configuration class | `AppConfig` tells Spring which beans to create. |
| Repository layer | `CourseRepository` stores the sample courses in memory. |
| Service layer | `EnrollmentService` applies enrollment rules such as unknown courses and full courses. |
| Menu-driven app | `App` reads user choices from the keyboard and calls Spring-managed services. |

## What You Need To Do Manually

Keep the manual work small:

1. Install Java 17 or newer if it is not already installed.
2. Install Maven if `mvn -version` does not work in the terminal.
3. Open a terminal in this repository and run the commands below.

I have already created the project files, package structure, Spring dependency, and run command configuration.

On Windows, if Maven is missing and `winget` cannot find it, download the Maven binary zip from the Apache Maven website, extract it into your user tools folder, and add its `bin` folder to PATH.

For this machine, Maven was extracted here:

```text
C:\Users\allte\tools\apache-maven-3.9.16
```

Add this folder to PATH:

```text
C:\Users\allte\tools\apache-maven-3.9.16\bin
```

## Files

| File | Purpose |
| --- | --- |
| [pom.xml](pom.xml) | Maven project file with the Spring Framework dependency and run plugin. |
| [src/main/java/com/workshop/springdemo/App.java](src/main/java/com/workshop/springdemo/App.java) | Starts the Spring container, gets services from Spring, and runs the console menu. |
| [src/main/java/com/workshop/springdemo/config/AppConfig.java](src/main/java/com/workshop/springdemo/config/AppConfig.java) | Defines repository and service objects as Spring beans. |
| [src/main/java/com/workshop/springdemo/model/Course.java](src/main/java/com/workshop/springdemo/model/Course.java) | Represents one course with a code, title, seat limit, and enrolled count. |
| [src/main/java/com/workshop/springdemo/model/EnrollmentResult.java](src/main/java/com/workshop/springdemo/model/EnrollmentResult.java) | Stores the result of one enrollment attempt. |
| [src/main/java/com/workshop/springdemo/repository/CourseRepository.java](src/main/java/com/workshop/springdemo/repository/CourseRepository.java) | Stores sample courses in memory and provides lookup methods. |
| [src/main/java/com/workshop/springdemo/service/EnrollmentService.java](src/main/java/com/workshop/springdemo/service/EnrollmentService.java) | Handles course enrollment and course creation rules. |
| [src/main/java/com/workshop/springdemo/service/ReportService.java](src/main/java/com/workshop/springdemo/service/ReportService.java) | Prints enrollment results and the available courses report. |

## Run The Project

From the repository root:

```powershell
cd .\day_6\spring_framework_demo
mvn clean compile exec:java
```

The app opens this menu:

```text
Starting Spring Course Enrollment System...

========== Course Enrollment Menu ==========
1. View all courses
2. Enroll a student
3. Add a new course
4. Exit
===========================================
Enter your choice:
```

Try this sample flow:

1. Choose `1` to view all courses.
2. Choose `2`, enter a student name, and enter course code `SPRING`.
3. Choose `3` to add a new course such as `HIBERNATE`.
4. Choose `1` again to see the new course in memory.
5. Choose `4` to exit.

## How To Present This Demo

1. `App.java` does not create repository or service objects manually. It asks Spring for them.
2. `AppConfig.java` defines the objects Spring should manage as beans.
3. `EnrollmentService` depends on `CourseRepository`, but it receives that dependency through its constructor.
4. `ReportService` depends on `EnrollmentService`, and Spring injects that dependency too.
5. The menu proves the app is interactive: the user can view courses, enroll students, add a course, and see runtime-only data change without a database.

## What To Try Next

After this works, try adding a search option, a delete course option, or a list of enrolled student names for each course.