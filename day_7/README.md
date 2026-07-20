<!-- markdownlint-disable -->

# Day 7 Study Guide - Spring Core, Spring MVC, JDBC, XML, and Annotations

This file explains the important theory and viva points for Day 7 Questions 1 to 4. The aim is to understand what each project is doing, why each important file exists, and what questions may be asked from the code.

## Big Picture

Day 7 mainly covers Spring Framework basics:

| Question | Main Topic | Type of Project |
| --- | --- | --- |
| Question 1 | Spring MVC with Spring JDBC and MySQL | Web application on Tomcat |
| Question 2 | Spring Core setter injection with Java configuration | Console Maven application |
| Question 3 | Spring Core XML configuration with list injection | Console Maven application |
| Question 4 | Spring Core annotation-based dependency injection | Console Maven application |

Question 1 is web-based because it uses JSP pages, a controller, Tomcat, and database search.

Questions 2, 3, and 4 are command-line Spring Core examples because they only demonstrate dependency injection and bean configuration.

## Common Terms

### POJO

POJO means Plain Old Java Object. It is a normal Java class with private fields, constructors if needed, getters, and setters. It does not need to extend any framework class.

Examples in Day 7:

| POJO | Purpose |
| --- | --- |
| `Employee` | Stores employee ID, name, department, and salary. |
| `Ink` | Stores ink brand, color, and type. |
| `Printer` | Stores printer details and depends on `Ink`. |
| `University` | Stores university name and department list. |
| `Student` | Stores student ID and student name. |
| `Course` | Stores course ID, course name, and faculty name. |

### Bean

A bean is an object created and managed by the Spring container.

In normal Java, we write:

```java
Printer printer = new Printer();
```

In Spring, the container creates and connects the object for us. We ask Spring for the bean when needed.

### Dependency

A dependency is an object that another class needs to work.

Examples:

| Class | Dependency |
| --- | --- |
| `Printer` | Needs `Ink`. |
| `Student` | Needs `Course`. |
| `EmployeeController` | Needs `EmployeeDAO`. |
| `EmployeeDAO` | Needs `JdbcTemplate`. |

### Dependency Injection

Dependency Injection means Spring gives the required object to a class instead of the class creating it manually.

Types used in Day 7:

| Type | Used In | Meaning |
| --- | --- | --- |
| Constructor injection | Question 1 | Dependency is passed through constructor. |
| Setter injection | Question 2 | Dependency is passed through setter method. |
| XML list injection | Question 3 | List values are injected from XML. |
| Annotation injection | Question 4 | Spring injects dependency using `@Autowired`. |

### Spring Container

The Spring container creates beans, stores beans, and injects dependencies.

Common containers used here:

| Class | Used In | Purpose |
| --- | --- | --- |
| `AnnotationConfigApplicationContext` | Questions 2 and 4 | Loads Java annotation-based configuration. |
| `ClassPathXmlApplicationContext` | Question 3 | Loads XML configuration from classpath. |
| `DispatcherServlet` | Question 1 | Front controller for Spring MVC web application. |

### Maven

Maven is a build tool for Java projects. It downloads dependencies, compiles code, and runs or packages the project.

Important Maven files and commands:

| Term | Meaning |
| --- | --- |
| `pom.xml` | Project Object Model file. It stores project details, dependencies, plugins, and build settings. |
| `mvn clean` | Deletes old build output from `target/`. |
| `mvn compile` | Compiles Java source files. |
| `mvn package` | Builds the final package, such as a WAR file for web apps. |
| `mvn exec:java` | Runs the configured main Java class. |
| `target/` | Maven-generated output folder. Do not submit it as source code. |

`mvn` is the Maven command. It is not Java itself. It calls Maven, and Maven internally uses Java and the dependencies from `pom.xml`.

### WAR File

WAR means Web Application Archive. It is used for Java web applications running on Tomcat.

Question 1 creates:

```text
EmployeeSearchPortal.war
```

This file is copied to:

```text
C:\xampp\tomcat\webapps\
```

Tomcat extracts it and runs it as a web application.

### JAR File

JAR means Java Archive. Console Maven applications normally create a JAR file. Questions 2, 3, and 4 are console projects, so they do not need Tomcat or WAR deployment.

### XML

XML is a structured configuration format. In Spring, XML can define beans and inject values.

Used in Day 7:

| XML File | Purpose |
| --- | --- |
| `web.xml` | Configures `DispatcherServlet` for Spring MVC. |
| `dispatcher-servlet.xml` | Configures Spring MVC, JDBC, DataSource, JdbcTemplate, DAO, and View Resolver. |
| `applicationContext.xml` | Defines the `University` bean and injects department names using `<list>`. |

### Annotation

Annotation is metadata written using `@` in Java.

Important annotations used:

| Annotation | Meaning |
| --- | --- |
| `@Controller` | Marks a Spring MVC controller class. |
| `@GetMapping` | Handles GET requests. |
| `@PostMapping` | Handles POST requests. |
| `@RequestParam` | Reads form/request parameter values. |
| `@Configuration` | Marks a Java class as Spring configuration. |
| `@Bean` | Creates a bean manually inside Java configuration. |
| `@Component` | Marks a class for automatic Spring bean creation. |
| `@ComponentScan` | Tells Spring which package to scan for components. |
| `@Autowired` | Injects a dependency automatically. |

## Question 1 - Employee Search Portal

### What It Does

This project lets the user enter an employee ID in a JSP page. Spring MVC receives the request, calls the DAO, searches the MySQL database, and displays employee details or `Employee not found.`.

### Main Flow

```text
search.jsp
    -> EmployeeController
        -> EmployeeDAO
            -> JdbcTemplate
                -> MySQL employee table
    -> employee.jsp
```

### Important Files

| File | Meaning |
| --- | --- |
| `search.jsp` | Form page where user enters employee ID. |
| `employee.jsp` | Result page that displays employee details or not-found message. |
| `Employee.java` | POJO/model class for employee data. |
| `EmployeeController.java` | Handles browser requests and chooses which view to show. |
| `EmployeeDAO.java` | Performs database operation using `JdbcTemplate`. |
| `dispatcher-servlet.xml` | Spring MVC and JDBC configuration. |
| `web.xml` | Registers Spring `DispatcherServlet`. |
| `database.sql` | Creates sample database table and records. |
| `pom.xml` | Maven dependencies and WAR packaging. |

### MVC Meaning

MVC means Model View Controller.

| Part | File Example | Role |
| --- | --- | --- |
| Model | `Employee.java` | Stores data. |
| View | `search.jsp`, `employee.jsp` | Displays UI. |
| Controller | `EmployeeController.java` | Handles request and response flow. |

### DAO Meaning

DAO means Data Access Object. It is responsible for database work.

In this project, `EmployeeDAO` searches the `employee` table by employee ID. Keeping database code in DAO makes the controller cleaner.

### JdbcTemplate

`JdbcTemplate` is a Spring JDBC helper class. It reduces repeated JDBC code such as opening statements, executing queries, and reading results.

It internally uses prepared statements when values are passed using placeholders like `?`.

Example idea:

```java
String sql = "SELECT employee_id, name, department, salary FROM employee WHERE employee_id = ?";
```

The `?` is replaced safely by the employee ID.

### DataSource

`DataSource` stores database connection details such as driver, URL, username, and password.

In this project:

| Setting | Value |
| --- | --- |
| Database | `NIET` |
| Username | `root` |
| Password | empty |
| URL | `jdbc:mysql://localhost:3306/NIET` |

### View Resolver

View Resolver converts logical view names into JSP file paths.

If controller returns:

```java
return "search";
```

Spring finds:

```text
/WEB-INF/views/search.jsp
```

because prefix is `/WEB-INF/views/` and suffix is `.jsp`.

### Why JSP Files Are Inside WEB-INF

Files inside `WEB-INF` cannot be opened directly from browser. They are shown through the controller. This is safer and follows MVC flow.

### How To Run Question 1

Start MySQL in XAMPP and run database setup:

```powershell
cmd /c "C:\xampp\mysql\bin\mysql.exe -u root < .\day_7\Question_1\EmployeeSearchPortal\database.sql"
```

Build the WAR:

```powershell
cd .\day_7\Question_1\EmployeeSearchPortal
mvn clean package
```

Copy WAR to Tomcat:

```powershell
copy .\target\EmployeeSearchPortal.war C:\xampp\tomcat\webapps\
```

Start Tomcat and open:

```text
http://localhost:8080/EmployeeSearchPortal/
```

### Likely Questions From Question 1

| Question | Short Answer |
| --- | --- |
| What is Spring MVC? | A Spring module for building web applications using Model, View, and Controller. |
| What is a controller? | A class that handles web requests and returns view names or data. |
| What is `@RequestParam`? | It reads request/form parameter values. |
| What is DAO? | A class that handles database operations. |
| What is POJO? | A normal Java object with fields, getters, and setters. |
| Why use `JdbcTemplate`? | It simplifies JDBC and internally handles prepared statements. |
| What does `Employee not found.` mean? | No employee record matched the entered ID. |
| Why use `web.xml`? | To register the Spring `DispatcherServlet`. |
| What is `DispatcherServlet`? | The front controller of Spring MVC. |
| What is a WAR file? | A deployable Java web application archive for Tomcat. |

## Question 2 - Printer Setter Injection

### What It Does

This project creates a `Printer` object that depends on an `Ink` object. Spring creates both beans and injects `Ink` into `Printer` using the setter method.

### Main Flow

```text
App.java
    -> loads AppConfig.java
        -> creates Ink bean
        -> creates Printer bean
        -> calls printer.setInk(ink)
    -> displays printer details
```

### Important Files

| File | Meaning |
| --- | --- |
| `Ink.java` | POJO for ink details. |
| `Printer.java` | POJO that depends on `Ink`. |
| `AppConfig.java` | Java-based Spring configuration. |
| `App.java` | Main class that starts Spring container. |
| `pom.xml` | Maven dependencies and run plugin. |

### Setter Injection

Setter injection means dependency is passed using a setter method.

In this project:

```java
printer.setInk(ink);
```

This means the `Ink` object is injected into the `Printer` object.

### Java-Based Configuration

Java-based configuration means beans are configured using Java class and annotations instead of XML.

Used annotations:

| Annotation | Meaning |
| --- | --- |
| `@Configuration` | Marks the class as Spring configuration. |
| `@Bean` | Method returns an object managed by Spring. |

### How To Run Question 2

```powershell
cd .\day_7\Question_2\PrinterSetterInjection
mvn clean compile exec:java
```

### Likely Questions From Question 2

| Question | Short Answer |
| --- | --- |
| What is setter injection? | Injecting dependency through a setter method. |
| Which class depends on which class? | `Printer` depends on `Ink`. |
| What does `@Bean` do? | It tells Spring to create and manage the returned object. |
| What does `@Configuration` do? | It marks a class as a source of bean definitions. |
| Why use Spring here? | To demonstrate dependency injection and bean management. |
| Is this a web project? | No, it is a console Spring Core project. |

## Question 3 - University List Injection Using XML

### What It Does

This project creates a `University` bean with a `List<String>` of department names. The list is injected from XML using the `<list>` tag.

### Main Flow

```text
App.java
    -> loads applicationContext.xml
        -> creates University bean
        -> injects department list
    -> displays departments
```

### Important Files

| File | Meaning |
| --- | --- |
| `University.java` | POJO with `List<String>` departments. |
| `applicationContext.xml` | XML bean configuration and list injection. |
| `App.java` | Main class that loads XML configuration. |
| `pom.xml` | Maven dependencies and run plugin. |

### List Injection

List injection means multiple values are injected into a Java `List` collection.

In XML:

```xml
<property name="departments">
    <list>
        <value>Computer Science</value>
        <value>Information Technology</value>
    </list>
</property>
```

Spring calls the setter method and passes a list of department names.

### ClassPathXmlApplicationContext

`ClassPathXmlApplicationContext` loads Spring XML configuration from `src/main/resources`.

In this project:

```java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
```

The XML file is copied by Maven into the classpath during build.

### How To Run Question 3

```powershell
cd .\day_7\Question_3\UniversityListInjection
mvn clean compile exec:java
```

### Likely Questions From Question 3

| Question | Short Answer |
| --- | --- |
| What is XML configuration? | Defining Spring beans in an XML file. |
| What is `<bean>`? | It defines a Spring-managed object. |
| What is `<property>`? | It injects a value through a setter method. |
| What is `<list>`? | It injects multiple values into a Java List. |
| Why is `applicationContext.xml` placed in resources? | So it is available on the classpath. |
| What is `ApplicationContext`? | Spring container interface used to access beans. |
| Is XML used in Question 4? | No, Question 4 uses annotations only. |

## Question 4 - Course Enrollment System Using Annotations

### What It Does

This project creates `Student` and `Course` as Spring components. Spring automatically injects `Course` into `Student` using `@Autowired`. The main class retrieves the `Student` bean and displays student and course details.

### Main Flow

```text
App.java
    -> loads AppConfig.java
        -> scans com.workshop.course package
        -> creates Course bean
        -> creates Student bean
        -> injects Course into Student using @Autowired
    -> displays student and course details
```

### Important Files

| File | Meaning |
| --- | --- |
| `Student.java` | Spring component with student details and `Course` dependency. |
| `Course.java` | Spring component with course details. |
| `AppConfig.java` | Annotation-based configuration using `@Configuration` and `@ComponentScan`. |
| `App.java` | Main class that loads `AnnotationConfigApplicationContext`. |
| `pom.xml` | Maven dependencies and run plugin. |

### Component Scanning

Component scanning means Spring searches a package for classes marked with annotations like `@Component`.

In this project:

```java
@ComponentScan(basePackages = "com.workshop.course")
```

Spring scans the package and creates beans for `Student` and `Course`.

### Autowired

`@Autowired` tells Spring to inject the required dependency automatically.

In this project, Spring injects `Course` into `Student`.

### Why No XML In Question 4

The question specifically says annotation-based configuration only and no XML. That is why `AppConfig.java` is used instead of `applicationContext.xml`.

### How To Run Question 4

```powershell
cd .\day_7\Question_4\CourseEnrollmentSystem
mvn clean compile exec:java
```

### Likely Questions From Question 4

| Question | Short Answer |
| --- | --- |
| What is `@Component`? | It marks a class as a Spring bean candidate. |
| What is `@Autowired`? | It injects dependency automatically. |
| What is `@ComponentScan`? | It tells Spring which package to scan for components. |
| What is `AnnotationConfigApplicationContext`? | Spring container for annotation-based Java configuration. |
| Why is no XML used? | The question asks for annotation-based configuration only. |
| Which class depends on which? | `Student` depends on `Course`. |

## Project File and Folder Names

| Name | Meaning |
| --- | --- |
| `src/main/java` | Main Java source code folder in Maven. |
| `src/main/resources` | Configuration/resource files copied to classpath. |
| `src/main/webapp` | Web application files for WAR projects. |
| `WEB-INF` | Protected web folder not directly accessible from browser. |
| `target` | Maven-generated build output. |
| `.java` | Java source file. |
| `.jsp` | Java Server Page view file. |
| `.xml` | XML configuration file. |
| `.sql` | SQL script file. |
| `.war` | Web application archive for Tomcat. |
| `.jar` | Java archive for normal Java applications. |

## Quick Difference Between Four Questions

| Topic | Question 1 | Question 2 | Question 3 | Question 4 |
| --- | --- | --- | --- | --- |
| Web app | Yes | No | No | No |
| Database | Yes | No | No | No |
| JSP | Yes | No | No | No |
| Spring MVC | Yes | No | No | No |
| Spring Core | Yes | Yes | Yes | Yes |
| Java config | No, mostly XML MVC config | Yes | No | Yes |
| XML config | Yes | No | Yes | No |
| Annotation config | Controller annotations | No component scan | No | Yes |
| Main command | `mvn clean package` | `mvn clean compile exec:java` | `mvn clean compile exec:java` | `mvn clean compile exec:java` |

## General Viva Questions

| Question | Short Answer |
| --- | --- |
| What is Spring Framework? | A Java framework that manages objects and dependencies. |
| What is IoC? | Inversion of Control means Spring controls object creation instead of the programmer. |
| What is DI? | Dependency Injection means Spring provides required objects to a class. |
| What is Maven? | A build and dependency management tool. |
| What is `pom.xml`? | Maven project configuration file. |
| What is Tomcat? | A server used to run Java web applications. |
| What is XAMPP used for here? | MySQL and Tomcat. |
| What is MySQL used for? | Storing employee data in Question 1. |
| What is JSP? | A server-side page used to display dynamic web content. |
| What is JDBC? | Java Database Connectivity API for connecting Java to databases. |
| What is Spring JDBC? | Spring helper module that simplifies JDBC code. |
| What should not be submitted as code? | `target/`, `.class`, `.war`, generated Maven files, and IDE files. |

## Final Revision Points

1. Question 1 demonstrates Spring MVC, database search, DAO, `JdbcTemplate`, JSP, and Tomcat deployment.
2. Question 2 demonstrates setter injection using Java-based configuration.
3. Question 3 demonstrates XML configuration and collection/list injection.
4. Question 4 demonstrates annotation-based configuration using `@Component`, `@Autowired`, `@Configuration`, and `@ComponentScan`.
5. `pom.xml` is needed in all Maven projects to provide Spring dependencies.
6. Console projects are run with `mvn clean compile exec:java`.
7. Web projects are packaged with `mvn clean package` and deployed to Tomcat as a WAR file.