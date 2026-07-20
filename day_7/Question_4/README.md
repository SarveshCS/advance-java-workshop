<!-- markdownlint-disable -->

# Question 4 - Course Enrollment System Using Spring Annotations

## Exact Question

Develop a Course Enrollment System using Spring Core and Annotation-Based Configuration.

Tasks:

1. Create a Maven project named `CourseEnrollmentSystem`.
2. Create `Student` and `Course` classes.
3. Use `@Component` for both classes.
4. Inject `Course` into `Student` using `@Autowired`.
5. Add Student ID, Student Name, Course ID, Course Name, and Faculty Name.
6. Create a configuration class using `@Configuration` and `@ComponentScan`.
7. Create a main class that loads `AnnotationConfigApplicationContext`, retrieves the `Student` bean, and displays student and course details.
8. Use annotation-based configuration only and do not use XML configuration.

## Solution Summary

This folder contains a Spring Core console Maven project named `CourseEnrollmentSystem`. It uses annotation-based configuration only. Spring finds `Student` and `Course` through component scanning, then injects the `Course` object into the `Student` object using `@Autowired`.

## Files

| File | Purpose |
| --- | --- |
| [CourseEnrollmentSystem/pom.xml](CourseEnrollmentSystem/pom.xml) | Maven project file with the Spring Context dependency and run plugin. |
| [CourseEnrollmentSystem/src/main/java/com/workshop/course/App.java](CourseEnrollmentSystem/src/main/java/com/workshop/course/App.java) | Starts the Spring container and displays student details. |
| [CourseEnrollmentSystem/src/main/java/com/workshop/course/config/AppConfig.java](CourseEnrollmentSystem/src/main/java/com/workshop/course/config/AppConfig.java) | Uses `@Configuration` and `@ComponentScan` for annotation-based configuration. |
| [CourseEnrollmentSystem/src/main/java/com/workshop/course/model/Student.java](CourseEnrollmentSystem/src/main/java/com/workshop/course/model/Student.java) | Student bean that receives the `Course` dependency using `@Autowired`. |
| [CourseEnrollmentSystem/src/main/java/com/workshop/course/model/Course.java](CourseEnrollmentSystem/src/main/java/com/workshop/course/model/Course.java) | Course bean with course ID, course name, and faculty name. |

## How to Run

From the repository root:

```powershell
cd .\day_7\Question_4\CourseEnrollmentSystem
mvn clean compile exec:java
```

Expected output:

```text
Student Details
Student ID: 101
Student Name: Aarav Sharma
Course ID: 501
Course Name: Advanced Java
Faculty Name: Dr. Sharma
```