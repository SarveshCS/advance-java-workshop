<!-- markdownlint-disable -->

# Question 3 - University List Injection Using Spring XML

## Exact Question

Create a `University` POJO with a `List<String>` attribute to hold department names. Use Spring's XML configuration for bean definition and inject the list values using the `<list>` tag. Access the configured bean via `ApplicationContext` in the main class and display the departments.

Instructions:

1. Create a new package in a previously made Maven project or create a new Maven project.
2. Include all relevant dependencies in `pom.xml`.
3. Do necessary changes in `App.java` and configuration files.

## Solution Summary

This folder contains a simple Spring Core Maven project named `UniversityListInjection`. The `University` POJO has a `List<String>` field named `departments`. The list values are injected through `applicationContext.xml` using the Spring `<list>` tag. The main class loads the bean through `ApplicationContext` and displays all department names.

## Files

| File | Purpose |
| --- | --- |
| [UniversityListInjection/pom.xml](UniversityListInjection/pom.xml) | Maven project file with the Spring Context dependency and run plugin. |
| [UniversityListInjection/src/main/java/com/workshop/university/App.java](UniversityListInjection/src/main/java/com/workshop/university/App.java) | Loads Spring XML configuration and displays the configured university departments. |
| [UniversityListInjection/src/main/java/com/workshop/university/model/University.java](UniversityListInjection/src/main/java/com/workshop/university/model/University.java) | POJO with university name and `List<String>` department names. |
| [UniversityListInjection/src/main/resources/applicationContext.xml](UniversityListInjection/src/main/resources/applicationContext.xml) | Spring XML configuration that defines the university bean and injects department names using `<list>`. |

## How to Run

From the repository root:

```powershell
cd .\day_7\Question_3\UniversityListInjection
mvn clean compile exec:java
```

Expected output:

```text
University Name: Noida Institute of Engineering and Technology
Departments:
Computer Science
Information Technology
Electronics and Communication
Mechanical Engineering
Civil Engineering
```