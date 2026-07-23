<!-- markdownlint-disable -->

# Advanced Java Workshop

This repository contains workshop materials and Java examples for Advanced Java practice.

## Workshop Index

Add each program, notes file, or exercise under the correct day section. This works like the index at the start of a book, so files are easy to find later.

### Repository Tools

| Topic | Description | File |
| --- | --- | --- |
| Ignore rules | Keeps local AI instructions, Java build output, and IDE files out of Git. | [.gitignore](.gitignore) |
| VS Code terminal setup | Opens new workspace terminals with repo Java commands already loaded. | [.vscode/settings.json](.vscode/settings.json) |
| Java VS Code tasks | Compile and run the active Java file using that day's `out/` directory. | [.vscode/tasks.json](.vscode/tasks.json) |
| Repo Java commands | Optional PowerShell commands that make `javac` and `java` use each day's `out/` folder automatically. | [.vscode/java-repo-commands.ps1](.vscode/java-repo-commands.ps1) |

### Day 1

| Topic | Description | File |
| --- | --- | --- |
| Student marks summary | Calculates total marks, average marks, highest marks, and grade using arrays, loops, methods, and conditions. | [day_1/StudentMarksSummary.java](day_1/StudentMarksSummary.java) |
| For-each array printing | Prints all elements from a 10-element integer array using a for-each loop. | [day_1/ForEachArrayExample.java](day_1/ForEachArrayExample.java) |
| JDBC connection | Connects to a MySQL database by reading the host, port, database name, username, and password from the keyboard. | [day_1/JdbcConnectionExample.java](day_1/JdbcConnectionExample.java) |

### Day1

| Topic | Description | File |
| --- | --- | --- |
| String | Cleans a raw student name, formats a display name, and generates a course username using Java String methods. | [Day1/StringExample.java](Day1/StringExample.java) |
| Array | Stores expense categories and amounts in arrays, then calculates total expense and the highest expense category. | [Day1/ArrayExample.java](Day1/ArrayExample.java) |
| Encapsulation | Models a bank account with private fields and controlled deposit, withdrawal, and getter methods. | [Day1/EncapsulationExample.java](Day1/EncapsulationExample.java) |
| Inheritance | Reuses common employee details in a parent class and extends them with full-time salary details. | [Day1/InheritanceExample.java](Day1/InheritanceExample.java) |
| Polymorphism | Uses one parent reference type to call different payment behavior for card and UPI payments. | [Day1/PolymorphismExample.java](Day1/PolymorphismExample.java) |
| Constructor | Creates course enrollment objects using a default constructor, parameterized constructor, and constructor chaining. | [Day1/ConstructorExample.java](Day1/ConstructorExample.java) |
| Abstraction | Defines a common report template with an abstract method implemented by sales and attendance reports. | [Day1/AbstractionExample.java](Day1/AbstractionExample.java) |

### Day 2

| Topic | Description | File |
| --- | --- | --- |
| JDBC user management | Creates a menu-driven user management system using JDBC, MySQL, SQL statements, and prepared statements. | [day_2/JdbcUserManagementSystem.java](day_2/JdbcUserManagementSystem.java) |
| Servlet and JSP setup notes | Explains the tools needed for Servlet/JSP development and how to build and run the demo on Tomcat. | [day_2/ServletJspTomcatSetupNotes.md](day_2/ServletJspTomcatSetupNotes.md) |
| Servlet request handling | Reads a submitted student name, creates a welcome message, and forwards the request to a JSP page. | [day_2/servlet_jsp_demo/src/main/java/HelloServlet.java](day_2/servlet_jsp_demo/src/main/java/HelloServlet.java) |
| JSP input form | Displays a simple web form that posts student input to the servlet. | [day_2/servlet_jsp_demo/src/main/webapp/index.jsp](day_2/servlet_jsp_demo/src/main/webapp/index.jsp) |
| JSP result page | Displays the message prepared by the servlet using JSP Expression Language. | [day_2/servlet_jsp_demo/src/main/webapp/result.jsp](day_2/servlet_jsp_demo/src/main/webapp/result.jsp) |

### Day 3

| Topic | Description | File |
| --- | --- | --- |
| Day 3 notes | Marks the Day 3 folder for future Advanced Java notes, exercises, and examples. | [day_3/README.md](day_3/README.md) |

### Day 4

| Topic | Description | File |
| --- | --- | --- |
| Question 1 guide | Explains the JDBC create, insert, and display answer with database setup and run commands. | [day_4/Question_1/README.md](day_4/Question_1/README.md) |
| JDBC create, insert, and display | Creates the `STUDENT` table, inserts 10 sample records, and displays all records using JDBC. | [day_4/Question_1/JdbcProject.java](day_4/Question_1/JdbcProject.java) |
| Question 2 guide | Explains the JDBC update, delete, insert, and display answer with database setup and run commands. | [day_4/Question_2/README.md](day_4/Question_2/README.md) |
| JDBC update, delete, and insert | Updates one student, deletes one student, inserts two students, and displays the updated `STUDENT` table. | [day_4/Question_2/JdbcProjectUpdate.java](day_4/Question_2/JdbcProjectUpdate.java) |
| Question 3 guide | Explains the JSP-only student information system, request forwarding, includes, and Tomcat run steps. | [day_4/Question_3/README.md](day_4/Question_3/README.md) |
| Student information form | Collects student name and roll number and submits them to `process.jsp`. | [day_4/Question_3/FirstDynamicProject/src/main/webapp/student.jsp](day_4/Question_3/FirstDynamicProject/src/main/webapp/student.jsp) |
| Student request processing | Reads form data, redirects empty fields, and forwards valid values to `display.jsp`. | [day_4/Question_3/FirstDynamicProject/src/main/webapp/process.jsp](day_4/Question_3/FirstDynamicProject/src/main/webapp/process.jsp) |
| Student information display | Includes the common header and footer and displays the submitted student details. | [day_4/Question_3/FirstDynamicProject/src/main/webapp/display.jsp](day_4/Question_3/FirstDynamicProject/src/main/webapp/display.jsp) |
| Student page header | Displays the common NIET header message. | [day_4/Question_3/FirstDynamicProject/src/main/webapp/header.jsp](day_4/Question_3/FirstDynamicProject/src/main/webapp/header.jsp) |
| Student page footer | Displays the common thank-you footer message. | [day_4/Question_3/FirstDynamicProject/src/main/webapp/footer.jsp](day_4/Question_3/FirstDynamicProject/src/main/webapp/footer.jsp) |
| Question 4 guide | Explains the JSP-only login system, credentials, session flow, and Tomcat run steps. | [day_4/Question_4/README.md](day_4/Question_4/README.md) |
| JSP login page | Collects email and password and posts them to `validate.jsp`. | [day_4/Question_4/FirstDynamicProject/src/main/webapp/login.jsp](day_4/Question_4/FirstDynamicProject/src/main/webapp/login.jsp) |
| JSP validation page | Checks predefined credentials, creates the HTTP session, and redirects or displays an error. | [day_4/Question_4/FirstDynamicProject/src/main/webapp/validate.jsp](day_4/Question_4/FirstDynamicProject/src/main/webapp/validate.jsp) |
| JSP profile page | Displays the NIET welcome message and logged-in email when a valid session exists. | [day_4/Question_4/FirstDynamicProject/src/main/webapp/profile.jsp](day_4/Question_4/FirstDynamicProject/src/main/webapp/profile.jsp) |
| JSP logout page | Invalidates the HTTP session, displays the logout message, and redirects back to login. | [day_4/Question_4/FirstDynamicProject/src/main/webapp/logout.jsp](day_4/Question_4/FirstDynamicProject/src/main/webapp/logout.jsp) |

### Day 5

| Topic | Description | File |
| --- | --- | --- |
| Todo web project guide | Explains the Servlet, JSP, JDBC, MySQL, and Tomcat todo list application with signup, login, logout, and dashboard features. | [day_5/todo_list_app/README.md](day_5/todo_list_app/README.md) |
| Todo Maven project | Builds the Day 5 dynamic web project as a WAR file for Tomcat. | [day_5/todo_list_app/pom.xml](day_5/todo_list_app/pom.xml) |
| Todo database utility | Connects to MySQL database `college_workshop` and automatically creates required user and todo tables. | [day_5/todo_list_app/src/main/java/com/workshop/todo/db/DatabaseUtil.java](day_5/todo_list_app/src/main/java/com/workshop/todo/db/DatabaseUtil.java) |
| Todo login servlet | Handles simple signup and login using users stored in the MySQL database. | [day_5/todo_list_app/src/main/java/com/workshop/todo/servlet/AuthServlet.java](day_5/todo_list_app/src/main/java/com/workshop/todo/servlet/AuthServlet.java) |
| Todo dashboard servlet | Loads the logged-in user's todo items with filtering and sorting options. | [day_5/todo_list_app/src/main/java/com/workshop/todo/servlet/DashboardServlet.java](day_5/todo_list_app/src/main/java/com/workshop/todo/servlet/DashboardServlet.java) |
| Todo action servlet | Adds, completes, deletes, and saves drag-and-drop order for todo items. | [day_5/todo_list_app/src/main/java/com/workshop/todo/servlet/TodoServlet.java](day_5/todo_list_app/src/main/java/com/workshop/todo/servlet/TodoServlet.java) |
| Todo HTML helper | Escapes user-entered todo text before it is displayed on JSP pages. | [day_5/todo_list_app/src/main/java/com/workshop/todo/util/HtmlUtil.java](day_5/todo_list_app/src/main/java/com/workshop/todo/util/HtmlUtil.java) |
| Todo login page | Displays the login form for existing users. | [day_5/todo_list_app/src/main/webapp/login.jsp](day_5/todo_list_app/src/main/webapp/login.jsp) |
| Todo signup page | Displays the signup form for new users. | [day_5/todo_list_app/src/main/webapp/signup.jsp](day_5/todo_list_app/src/main/webapp/signup.jsp) |
| Todo dashboard page | Shows the protected modern todo dashboard with analytics, editable tasks, theme switching, hide completed, sorting, and automatic drag-and-drop reorder. | [day_5/todo_list_app/src/main/webapp/dashboard.jsp](day_5/todo_list_app/src/main/webapp/dashboard.jsp) |

### Day 6

| Topic | Description | File |
| --- | --- | --- |
| Spring Framework project guide | Explains the interactive Spring Core course enrollment demo, manual setup needs, files, run command, and presentation points. | [day_6/spring_framework_demo/README.md](day_6/spring_framework_demo/README.md) |
| Spring Maven project | Builds and runs a Spring Framework dependency injection demo. | [day_6/spring_framework_demo/pom.xml](day_6/spring_framework_demo/pom.xml) |
| Spring application starter | Starts the Spring container, gets service beans, and runs the menu-driven console app. | [day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/App.java](day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/App.java) |
| Spring Java configuration | Defines the repository and service objects as Spring beans. | [day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/config/AppConfig.java](day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/config/AppConfig.java) |
| Spring course model | Represents one course with a code, title, seat limit, and enrollment count. | [day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/model/Course.java](day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/model/Course.java) |
| Spring enrollment result model | Stores whether one student enrollment attempt succeeded or failed. | [day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/model/EnrollmentResult.java](day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/model/EnrollmentResult.java) |
| Spring course repository | Stores sample and user-added courses in memory while the program is running. | [day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/repository/CourseRepository.java](day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/repository/CourseRepository.java) |
| Spring enrollment service | Applies enrollment and course creation rules such as full courses and unknown course codes. | [day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/service/EnrollmentService.java](day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/service/EnrollmentService.java) |
| Spring report service | Prints enrollment results and the available courses report. | [day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/service/ReportService.java](day_6/spring_framework_demo/src/main/java/com/workshop/springdemo/service/ReportService.java) |
| Spring Boot project guide | Explains the Spring Boot REST API demo, auto-configuration, embedded server, endpoints, and run commands. | [day_6/spring_boot_demo/README.md](day_6/spring_boot_demo/README.md) |
| Spring Boot Maven project | Builds and runs a Spring Boot web application with embedded Tomcat. | [day_6/spring_boot_demo/pom.xml](day_6/spring_boot_demo/pom.xml) |
| Spring Boot application starter | Starts the Spring Boot application using `SpringApplication.run(...)`. | [day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/SpringBootDemoApplication.java](day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/SpringBootDemoApplication.java) |
| Spring Boot REST controller | Defines endpoints for viewing courses, adding courses, and enrolling students. | [day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/controller/CourseController.java](day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/controller/CourseController.java) |
| Spring Boot course request DTO | Carries JSON request data for creating a new course. | [day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/dto/CourseRequest.java](day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/dto/CourseRequest.java) |
| Spring Boot enrollment request DTO | Carries JSON request data for enrolling a student. | [day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/dto/EnrollmentRequest.java](day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/dto/EnrollmentRequest.java) |
| Spring Boot course model | Represents a course returned as JSON by the API. | [day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/model/Course.java](day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/model/Course.java) |
| Spring Boot enrollment result model | Stores whether one API enrollment request succeeded or failed. | [day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/model/EnrollmentResult.java](day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/model/EnrollmentResult.java) |
| Spring Boot course repository | Stores sample courses in memory as a Spring `@Repository` component. | [day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/repository/CourseRepository.java](day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/repository/CourseRepository.java) |
| Spring Boot enrollment service | Applies course creation and enrollment rules as a Spring `@Service` component. | [day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/service/EnrollmentService.java](day_6/spring_boot_demo/src/main/java/com/workshop/springbootdemo/service/EnrollmentService.java) |
| Spring Boot context test | Verifies that the Spring Boot application context starts correctly. | [day_6/spring_boot_demo/src/test/java/com/workshop/springbootdemo/SpringBootDemoApplicationTests.java](day_6/spring_boot_demo/src/test/java/com/workshop/springbootdemo/SpringBootDemoApplicationTests.java) |

### Day 7

| Topic | Description | File |
| --- | --- | --- |
| Question 1 guide | Explains the Spring MVC Employee Search Portal with MySQL setup, Maven build, and Tomcat run steps. | [day_7/Question_1/README.md](day_7/Question_1/README.md) |
| Employee Search Maven project | Builds the Spring MVC and Spring JDBC employee search application as a WAR file for Tomcat. | [day_7/Question_1/EmployeeSearchPortal/pom.xml](day_7/Question_1/EmployeeSearchPortal/pom.xml) |
| Employee controller | Receives Employee ID using `@RequestParam`, calls the DAO, and returns employee details or a not-found message. | [day_7/Question_1/EmployeeSearchPortal/src/main/java/com/workshop/employee/controller/EmployeeController.java](day_7/Question_1/EmployeeSearchPortal/src/main/java/com/workshop/employee/controller/EmployeeController.java) |
| Employee DAO | Uses Spring JDBC `JdbcTemplate` to fetch employee records from the MySQL `employee` table. | [day_7/Question_1/EmployeeSearchPortal/src/main/java/com/workshop/employee/dao/EmployeeDAO.java](day_7/Question_1/EmployeeSearchPortal/src/main/java/com/workshop/employee/dao/EmployeeDAO.java) |
| Employee POJO | Stores employee ID, name, department, and salary values. | [day_7/Question_1/EmployeeSearchPortal/src/main/java/com/workshop/employee/model/Employee.java](day_7/Question_1/EmployeeSearchPortal/src/main/java/com/workshop/employee/model/Employee.java) |
| Spring MVC configuration | Configures component scanning, DataSource, JdbcTemplate, EmployeeDAO, and View Resolver. | [day_7/Question_1/EmployeeSearchPortal/src/main/webapp/WEB-INF/dispatcher-servlet.xml](day_7/Question_1/EmployeeSearchPortal/src/main/webapp/WEB-INF/dispatcher-servlet.xml) |
| Employee search page | Displays the JSP form for entering Employee ID and submitting the search request. | [day_7/Question_1/EmployeeSearchPortal/src/main/webapp/WEB-INF/views/search.jsp](day_7/Question_1/EmployeeSearchPortal/src/main/webapp/WEB-INF/views/search.jsp) |
| Employee result page | Displays employee details when found, or `Employee not found.` when no record matches. | [day_7/Question_1/EmployeeSearchPortal/src/main/webapp/WEB-INF/views/employee.jsp](day_7/Question_1/EmployeeSearchPortal/src/main/webapp/WEB-INF/views/employee.jsp) |
| Employee database setup | Creates the `employee` table and inserts sample records for testing the search portal. | [day_7/Question_1/EmployeeSearchPortal/database.sql](day_7/Question_1/EmployeeSearchPortal/database.sql) |
| Question 2 guide | Explains the Spring setter injection Printer and Ink example with Maven run steps. | [day_7/Question_2/README.md](day_7/Question_2/README.md) |
| Printer Setter Injection Maven project | Builds and runs the Spring Core setter injection example. | [day_7/Question_2/PrinterSetterInjection/pom.xml](day_7/Question_2/PrinterSetterInjection/pom.xml) |
| Printer application starter | Starts the Spring container and displays the printer details. | [day_7/Question_2/PrinterSetterInjection/src/main/java/com/workshop/printer/App.java](day_7/Question_2/PrinterSetterInjection/src/main/java/com/workshop/printer/App.java) |
| Printer Java configuration | Creates the `Ink` and `Printer` beans and injects ink using the printer setter method. | [day_7/Question_2/PrinterSetterInjection/src/main/java/com/workshop/printer/config/AppConfig.java](day_7/Question_2/PrinterSetterInjection/src/main/java/com/workshop/printer/config/AppConfig.java) |
| Ink model | Stores ink brand, color, and type details. | [day_7/Question_2/PrinterSetterInjection/src/main/java/com/workshop/printer/model/Ink.java](day_7/Question_2/PrinterSetterInjection/src/main/java/com/workshop/printer/model/Ink.java) |
| Printer model | Stores printer details and receives the `Ink` dependency using setter injection. | [day_7/Question_2/PrinterSetterInjection/src/main/java/com/workshop/printer/model/Printer.java](day_7/Question_2/PrinterSetterInjection/src/main/java/com/workshop/printer/model/Printer.java) |
| Question 3 guide | Explains the Spring XML list injection University example with Maven run steps. | [day_7/Question_3/README.md](day_7/Question_3/README.md) |
| University List Injection Maven project | Builds and runs the Spring Core XML list injection example. | [day_7/Question_3/UniversityListInjection/pom.xml](day_7/Question_3/UniversityListInjection/pom.xml) |
| University application starter | Loads the Spring XML configuration through `ApplicationContext` and displays departments. | [day_7/Question_3/UniversityListInjection/src/main/java/com/workshop/university/App.java](day_7/Question_3/UniversityListInjection/src/main/java/com/workshop/university/App.java) |
| University POJO | Stores university name and a `List<String>` of department names. | [day_7/Question_3/UniversityListInjection/src/main/java/com/workshop/university/model/University.java](day_7/Question_3/UniversityListInjection/src/main/java/com/workshop/university/model/University.java) |
| Spring XML list configuration | Defines the `University` bean and injects department names using the `<list>` tag. | [day_7/Question_3/UniversityListInjection/src/main/resources/applicationContext.xml](day_7/Question_3/UniversityListInjection/src/main/resources/applicationContext.xml) |
| Question 4 guide | Explains the Spring annotation-based Course Enrollment System with Maven run steps. | [day_7/Question_4/README.md](day_7/Question_4/README.md) |
| Course Enrollment Maven project | Builds and runs the Spring Core annotation-based dependency injection example. | [day_7/Question_4/CourseEnrollmentSystem/pom.xml](day_7/Question_4/CourseEnrollmentSystem/pom.xml) |
| Course enrollment application starter | Loads `AnnotationConfigApplicationContext`, retrieves the `Student` bean, and displays details. | [day_7/Question_4/CourseEnrollmentSystem/src/main/java/com/workshop/course/App.java](day_7/Question_4/CourseEnrollmentSystem/src/main/java/com/workshop/course/App.java) |
| Course enrollment configuration | Uses `@Configuration` and `@ComponentScan` for annotation-based Spring setup. | [day_7/Question_4/CourseEnrollmentSystem/src/main/java/com/workshop/course/config/AppConfig.java](day_7/Question_4/CourseEnrollmentSystem/src/main/java/com/workshop/course/config/AppConfig.java) |
| Course bean | Stores course ID, course name, and faculty name as a Spring `@Component`. | [day_7/Question_4/CourseEnrollmentSystem/src/main/java/com/workshop/course/model/Course.java](day_7/Question_4/CourseEnrollmentSystem/src/main/java/com/workshop/course/model/Course.java) |
| Student bean | Stores student details and receives the `Course` dependency using `@Autowired`. | [day_7/Question_4/CourseEnrollmentSystem/src/main/java/com/workshop/course/model/Student.java](day_7/Question_4/CourseEnrollmentSystem/src/main/java/com/workshop/course/model/Student.java) |

### Day 8

| Topic | Description | File |
| --- | --- | --- |
| School management guide | Explains the Spring Boot, JPA, MySQL, and Thymeleaf school management system with run steps. | [day_8/README.md](day_8/README.md) |
| School management Maven project | Builds and runs the Spring Boot school management system. | [day_8/school_management_system/pom.xml](day_8/school_management_system/pom.xml) |
| School management properties | Configures embedded server port, MySQL database, and JPA table creation. | [day_8/school_management_system/src/main/resources/application.properties](day_8/school_management_system/src/main/resources/application.properties) |
| School management application starter | Starts the Spring Boot application. | [day_8/school_management_system/src/main/java/com/workshop/school/SchoolManagementApplication.java](day_8/school_management_system/src/main/java/com/workshop/school/SchoolManagementApplication.java) |
| School data seeder | Creates the default admin account and sample teachers, subjects, classes, and students. | [day_8/school_management_system/src/main/java/com/workshop/school/config/DataSeeder.java](day_8/school_management_system/src/main/java/com/workshop/school/config/DataSeeder.java) |
| Admin entity | Stores the single admin login record. | [day_8/school_management_system/src/main/java/com/workshop/school/model/Admin.java](day_8/school_management_system/src/main/java/com/workshop/school/model/Admin.java) |
| Student entity | Stores student details and class assignment using JPA. | [day_8/school_management_system/src/main/java/com/workshop/school/model/Student.java](day_8/school_management_system/src/main/java/com/workshop/school/model/Student.java) |
| Teacher entity | Stores teacher details and assigned subject text. | [day_8/school_management_system/src/main/java/com/workshop/school/model/Teacher.java](day_8/school_management_system/src/main/java/com/workshop/school/model/Teacher.java) |
| Subject entity | Stores subject name and subject code. | [day_8/school_management_system/src/main/java/com/workshop/school/model/Subject.java](day_8/school_management_system/src/main/java/com/workshop/school/model/Subject.java) |
| School class entity | Stores class name, section, class teacher, and multiple subjects. | [day_8/school_management_system/src/main/java/com/workshop/school/model/SchoolClass.java](day_8/school_management_system/src/main/java/com/workshop/school/model/SchoolClass.java) |
| Admin repository | Finds the seeded admin by username for login. | [day_8/school_management_system/src/main/java/com/workshop/school/repository/AdminRepository.java](day_8/school_management_system/src/main/java/com/workshop/school/repository/AdminRepository.java) |
| Student repository | Provides student CRUD and lookup by class. | [day_8/school_management_system/src/main/java/com/workshop/school/repository/StudentRepository.java](day_8/school_management_system/src/main/java/com/workshop/school/repository/StudentRepository.java) |
| Teacher repository | Provides teacher CRUD. | [day_8/school_management_system/src/main/java/com/workshop/school/repository/TeacherRepository.java](day_8/school_management_system/src/main/java/com/workshop/school/repository/TeacherRepository.java) |
| Subject repository | Provides subject CRUD. | [day_8/school_management_system/src/main/java/com/workshop/school/repository/SubjectRepository.java](day_8/school_management_system/src/main/java/com/workshop/school/repository/SubjectRepository.java) |
| School class repository | Provides class CRUD and class teacher lookup. | [day_8/school_management_system/src/main/java/com/workshop/school/repository/SchoolClassRepository.java](day_8/school_management_system/src/main/java/com/workshop/school/repository/SchoolClassRepository.java) |
| Authentication controller | Handles admin login, logout, and home redirects. | [day_8/school_management_system/src/main/java/com/workshop/school/controller/AuthController.java](day_8/school_management_system/src/main/java/com/workshop/school/controller/AuthController.java) |
| Dashboard controller | Shows dashboard counts and recent records. | [day_8/school_management_system/src/main/java/com/workshop/school/controller/DashboardController.java](day_8/school_management_system/src/main/java/com/workshop/school/controller/DashboardController.java) |
| Student controller | Handles student list, create, edit, save, and delete actions. | [day_8/school_management_system/src/main/java/com/workshop/school/controller/StudentController.java](day_8/school_management_system/src/main/java/com/workshop/school/controller/StudentController.java) |
| Teacher controller | Handles teacher list, create, edit, save, and delete actions. | [day_8/school_management_system/src/main/java/com/workshop/school/controller/TeacherController.java](day_8/school_management_system/src/main/java/com/workshop/school/controller/TeacherController.java) |
| Subject controller | Handles subject list, create, edit, save, and delete actions. | [day_8/school_management_system/src/main/java/com/workshop/school/controller/SubjectController.java](day_8/school_management_system/src/main/java/com/workshop/school/controller/SubjectController.java) |
| School class controller | Handles class CRUD, class teacher assignment, and subject assignment. | [day_8/school_management_system/src/main/java/com/workshop/school/controller/SchoolClassController.java](day_8/school_management_system/src/main/java/com/workshop/school/controller/SchoolClassController.java) |
| School management theme | Provides the single polished light theme for the app. | [day_8/school_management_system/src/main/resources/static/css/app.css](day_8/school_management_system/src/main/resources/static/css/app.css) |
| Admin login page | Displays the admin login form. | [day_8/school_management_system/src/main/resources/templates/login.html](day_8/school_management_system/src/main/resources/templates/login.html) |
| Admin dashboard page | Displays summary cards and recent students. | [day_8/school_management_system/src/main/resources/templates/dashboard.html](day_8/school_management_system/src/main/resources/templates/dashboard.html) |
| Student pages | Provide student list and form screens. | [day_8/school_management_system/src/main/resources/templates/students/list.html](day_8/school_management_system/src/main/resources/templates/students/list.html) |
| Teacher pages | Provide teacher list and form screens. | [day_8/school_management_system/src/main/resources/templates/teachers/list.html](day_8/school_management_system/src/main/resources/templates/teachers/list.html) |
| Class pages | Provide class list and form screens. | [day_8/school_management_system/src/main/resources/templates/classes/list.html](day_8/school_management_system/src/main/resources/templates/classes/list.html) |
| Subject pages | Provide subject list and form screens. | [day_8/school_management_system/src/main/resources/templates/subjects/list.html](day_8/school_management_system/src/main/resources/templates/subjects/list.html) |

### Day 9

| Topic | Description | File |
| --- | --- | --- |
| Day 9 guide | Explains the Assessment 3 question paper, submission rules, Spring Boot/JPA/H2 theory, and the index of attempted questions. | [day_9/README.md](day_9/README.md) |
| Question 1 guide | Explains the Spring Boot, Spring Data JPA, H2, and Lombok Student Management System with Spring Initializr steps, run steps, and verified output. | [day_9/Question_1/README.md](day_9/Question_1/README.md) |
| Student Management Maven project | Builds and runs the Spring Boot REST API with an embedded H2 database. | [day_9/Question_1/StudentManagementSystem/pom.xml](day_9/Question_1/StudentManagementSystem/pom.xml) |
| Student Management properties | Configures the server port and the H2 in-memory database connection. | [day_9/Question_1/StudentManagementSystem/src/main/resources/application.properties](day_9/Question_1/StudentManagementSystem/src/main/resources/application.properties) |
| Student Management application starter | Starts the Spring Boot application. | [day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/StudentManagementSystemApplication.java](day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/StudentManagementSystemApplication.java) |
| Student entity | JPA entity for student records, using Lombok for getters, setters, and constructors. | [day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/model/Student.java](day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/model/Student.java) |
| Student repository | Spring Data JPA repository providing save and find operations for `Student`. | [day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/repository/StudentRepository.java](day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/repository/StudentRepository.java) |
| Student REST controller | Exposes save, list, and find-by-id endpoints for students. | [day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/controller/StudentController.java](day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/controller/StudentController.java) |
| Student not found exception | Custom exception thrown when a requested student ID does not exist. | [day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/exception/StudentNotFoundException.java](day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/exception/StudentNotFoundException.java) |
| API error response | Formatted error response shape with timestamp, status, and message. | [day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/exception/ApiError.java](day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/exception/ApiError.java) |
| Global exception handler | Catches exceptions across every controller and returns a formatted JSON error. | [day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/exception/GlobalExceptionHandler.java](day_9/Question_1/StudentManagementSystem/src/main/java/com/workshop/student/exception/GlobalExceptionHandler.java) |
| Question 2 guide | Explains the Spring Data JPA CRUD Product application with the service layer, HTTP status codes, and Postman testing steps. | [day_9/Question_2/README.md](day_9/Question_2/README.md) |
| Product CRUD Maven project | Builds and runs the Spring Boot CRUD API backed by MySQL. | [day_9/Question_2/ProductCrudApp/pom.xml](day_9/Question_2/ProductCrudApp/pom.xml) |
| Product CRUD properties | Configures the server port and MySQL database connection. | [day_9/Question_2/ProductCrudApp/src/main/resources/application.properties](day_9/Question_2/ProductCrudApp/src/main/resources/application.properties) |
| Product CRUD application starter | Starts the Spring Boot application. | [day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/ProductCrudAppApplication.java](day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/ProductCrudAppApplication.java) |
| Product entity | JPA entity representing a product. | [day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/model/Product.java](day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/model/Product.java) |
| Product repository | Spring Data JPA repository for `Product`. | [day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/repository/ProductRepository.java](day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/repository/ProductRepository.java) |
| Product service | Business logic for create, read, update, and delete operations. | [day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/service/ProductService.java](day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/service/ProductService.java) |
| Product REST controller | Exposes the five CRUD endpoints with correct HTTP status codes. | [day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/controller/ProductController.java](day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/controller/ProductController.java) |
| Product not found exception | Custom exception thrown when a requested product ID does not exist. | [day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/exception/ProductNotFoundException.java](day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/exception/ProductNotFoundException.java) |
| Product API error response | Formatted error response shape with timestamp, status, and message. | [day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/exception/ApiError.java](day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/exception/ApiError.java) |
| Product global exception handler | Catches exceptions across every controller and returns a formatted JSON error. | [day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/exception/GlobalExceptionHandler.java](day_9/Question_2/ProductCrudApp/src/main/java/com/workshop/product/exception/GlobalExceptionHandler.java) |
| Question 3 guide | Explains the Book Record API with `@ControllerAdvice`, two custom exceptions, and minimal Postman testing steps. | [day_9/Question_3/README.md](day_9/Question_3/README.md) |
| Book Record Maven project | Builds and runs the Spring Boot REST API backed by MySQL. | [day_9/Question_3/BookRecordApi/pom.xml](day_9/Question_3/BookRecordApi/pom.xml) |
| Book Record properties | Configures the server port and MySQL database connection. | [day_9/Question_3/BookRecordApi/src/main/resources/application.properties](day_9/Question_3/BookRecordApi/src/main/resources/application.properties) |
| Book Record application starter | Starts the Spring Boot application. | [day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/BookRecordApiApplication.java](day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/BookRecordApiApplication.java) |
| Book entity | JPA entity representing a book record. | [day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/model/Book.java](day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/model/Book.java) |
| Book repository | Spring Data JPA repository, including a lookup by ISBN. | [day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/repository/BookRepository.java](day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/repository/BookRepository.java) |
| Book service | Business logic for CRUD operations and duplicate ISBN checking. | [day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/service/BookService.java](day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/service/BookService.java) |
| Book REST controller | Exposes the CRUD endpoints for books. | [day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/controller/BookController.java](day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/controller/BookController.java) |
| Book not found exception | Custom exception thrown when a requested book ID does not exist. | [day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/exception/BookNotFoundException.java](day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/exception/BookNotFoundException.java) |
| Duplicate ISBN exception | Custom exception thrown when a book with the same ISBN already exists. | [day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/exception/DuplicateIsbnException.java](day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/exception/DuplicateIsbnException.java) |
| Book API error response | Formatted error response shape with timestamp, status, and message. | [day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/exception/ApiError.java](day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/exception/ApiError.java) |
| Book global exception handler | Uses `@ControllerAdvice` to catch both custom exceptions and return meaningful HTTP status codes. | [day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/exception/GlobalExceptionHandler.java](day_9/Question_3/BookRecordApi/src/main/java/com/workshop/book/exception/GlobalExceptionHandler.java) |
| Question 4 guide | Explains the Lombok-based Course Info app, static content auto-configuration, and the formatted HTML output endpoint. | [day_9/Question_4/README.md](day_9/Question_4/README.md) |
| Course Info Maven project | Builds and runs the Spring Boot app with Spring Web and Lombok dependencies. | [day_9/Question_4/CourseInfoApp/pom.xml](day_9/Question_4/CourseInfoApp/pom.xml) |
| Course Info properties | Sets the embedded server port. | [day_9/Question_4/CourseInfoApp/src/main/resources/application.properties](day_9/Question_4/CourseInfoApp/src/main/resources/application.properties) |
| Course Info static page | Static HTML welcome page served automatically by Spring Boot with no controller code. | [day_9/Question_4/CourseInfoApp/src/main/resources/static/index.html](day_9/Question_4/CourseInfoApp/src/main/resources/static/index.html) |
| Course Info application starter | Starts the Spring Boot application. | [day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/CourseInfoAppApplication.java](day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/CourseInfoAppApplication.java) |
| Course model | Course data class built entirely with Lombok annotations. | [day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/model/Course.java](day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/model/Course.java) |
| Course service | Holds the in-memory list of sample courses and looks one up by ID. | [day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/service/CourseService.java](day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/service/CourseService.java) |
| Course controller | Serves course data as JSON and as a formatted HTML table. | [day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/controller/CourseController.java](day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/controller/CourseController.java) |
| Course not found exception | Custom exception thrown when a requested course ID does not exist. | [day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/exception/CourseNotFoundException.java](day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/exception/CourseNotFoundException.java) |
| Course API error response | Formatted error response shape with timestamp, status, and message. | [day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/exception/ApiError.java](day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/exception/ApiError.java) |
| Course global exception handler | Converts `CourseNotFoundException` into a formatted `404` JSON response. | [day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/exception/GlobalExceptionHandler.java](day_9/Question_4/CourseInfoApp/src/main/java/com/workshop/course/exception/GlobalExceptionHandler.java) |

## Running Java Programs

Create day folders using this pattern:

```text
day_1/
day_2/
day_3/
```

Put that day's Java files inside the matching folder.

### Option 1: Repo Java Commands

New VS Code terminals load the repo Java commands automatically. In an already-open PowerShell terminal, run this once from the repository root:

```powershell
. .\.vscode\java-repo-commands.ps1
```

After that, normal commands become simpler inside this terminal session:

```powershell
javac .\day_1\HelloWorld.java
java HelloWorld
```

The custom `javac` command compiles to `day_1/out/`. The custom `java` command uses the current folder's `out/` folder, or finds the matching class in a day folder's `out/` directory when there is only one match.

To use the real commands without this behavior, pass output/classpath manually:

```powershell
javac -d .\somewhere .\day_1\HelloWorld.java
java -cp .\day_1\out HelloWorld
```

### Option 2: VS Code Tasks

To compile and run with that same day's `out/` folder, open the Java file and run this VS Code task:

```text
Terminal > Run Task > Java: run active file from day out
```

The task compiles to `day_1/out/` and then runs the class from that folder. The build task is also the default build task, so `Terminal > Run Build Task` compiles the open Java file into its day folder's `out/` directory.

Manual command pattern:

```powershell
javac -d .\day_1\out .\day_1\HelloWorld.java
java -cp .\day_1\out HelloWorld
```

