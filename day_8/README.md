<!-- markdownlint-disable -->

# Day 8 - School Management System

## Project Summary

This folder contains a Spring Boot School Management System built for a college project showcase. It uses Spring Boot, Spring MVC, Spring Data JPA, Thymeleaf, and the MySQL/MariaDB database server from XAMPP.

The app has one admin login. After login, the admin can manage students, teachers, classes, and subjects. Students and teachers do not have separate login accounts.

## Main Features

| Feature | Description |
| --- | --- |
| Admin login/logout | Uses one seeded admin account with session-based login. |
| Dashboard | Shows total students, teachers, classes, and subjects. |
| Student CRUD | Add, view, edit, delete, and assign students to classes. |
| Teacher CRUD | Add, view, edit, delete teacher records, and assign a teacher to an existing class. |
| Subject CRUD | Add, view, edit, and delete subjects. |
| Class CRUD | Add, view, edit, delete classes, assign class teacher, and assign multiple subjects. |
| Searchable assignments | Teacher, student, and class forms use searchable pickers for existing classes or teachers. |
| JPA database setup | Creates database tables automatically using Spring Data JPA and Hibernate. |

## Login Details

| Field | Value |
| --- | --- |
| Username | `admin` |
| Password | `admin123` |

## Database Details

| Setting | Value |
| --- | --- |
| Database | `school_management_system` |
| Username | `root` |
| Password | empty password |
| Port | `3306` |

The database is created automatically because the JDBC URL includes `createDatabaseIfNotExist=true`. Tables are created by JPA using `spring.jpa.hibernate.ddl-auto=update`.

## Files

| File | Purpose |
| --- | --- |
| [school_management_system/pom.xml](school_management_system/pom.xml) | Maven project with Spring Boot, Thymeleaf, JPA, and MySQL dependencies. |
| [school_management_system/src/main/resources/application.properties](school_management_system/src/main/resources/application.properties) | Configures server port, MySQL connection, and JPA table creation. |
| [school_management_system/src/main/java/com/workshop/school/SchoolManagementApplication.java](school_management_system/src/main/java/com/workshop/school/SchoolManagementApplication.java) | Starts the Spring Boot application. |
| [school_management_system/src/main/java/com/workshop/school/config/DataSeeder.java](school_management_system/src/main/java/com/workshop/school/config/DataSeeder.java) | Seeds the admin account and sample school records. |
| [school_management_system/src/main/java/com/workshop/school/model/Admin.java](school_management_system/src/main/java/com/workshop/school/model/Admin.java) | JPA entity for admin login data. |
| [school_management_system/src/main/java/com/workshop/school/model/Student.java](school_management_system/src/main/java/com/workshop/school/model/Student.java) | JPA entity for student records. |
| [school_management_system/src/main/java/com/workshop/school/model/Teacher.java](school_management_system/src/main/java/com/workshop/school/model/Teacher.java) | JPA entity for teacher records. |
| [school_management_system/src/main/java/com/workshop/school/model/Subject.java](school_management_system/src/main/java/com/workshop/school/model/Subject.java) | JPA entity for subject records. |
| [school_management_system/src/main/java/com/workshop/school/model/SchoolClass.java](school_management_system/src/main/java/com/workshop/school/model/SchoolClass.java) | JPA entity for class records, class teacher, and class subjects. |
| [school_management_system/src/main/java/com/workshop/school/repository/AdminRepository.java](school_management_system/src/main/java/com/workshop/school/repository/AdminRepository.java) | Repository for admin login lookup. |
| [school_management_system/src/main/java/com/workshop/school/repository/StudentRepository.java](school_management_system/src/main/java/com/workshop/school/repository/StudentRepository.java) | Repository for student CRUD. |
| [school_management_system/src/main/java/com/workshop/school/repository/TeacherRepository.java](school_management_system/src/main/java/com/workshop/school/repository/TeacherRepository.java) | Repository for teacher CRUD. |
| [school_management_system/src/main/java/com/workshop/school/repository/SubjectRepository.java](school_management_system/src/main/java/com/workshop/school/repository/SubjectRepository.java) | Repository for subject CRUD. |
| [school_management_system/src/main/java/com/workshop/school/repository/SchoolClassRepository.java](school_management_system/src/main/java/com/workshop/school/repository/SchoolClassRepository.java) | Repository for class CRUD. |
| [school_management_system/src/main/java/com/workshop/school/controller/AuthController.java](school_management_system/src/main/java/com/workshop/school/controller/AuthController.java) | Handles login, logout, and home redirects. |
| [school_management_system/src/main/java/com/workshop/school/controller/DashboardController.java](school_management_system/src/main/java/com/workshop/school/controller/DashboardController.java) | Shows dashboard counts and recent students. |
| [school_management_system/src/main/java/com/workshop/school/controller/StudentController.java](school_management_system/src/main/java/com/workshop/school/controller/StudentController.java) | Handles student CRUD screens. |
| [school_management_system/src/main/java/com/workshop/school/controller/TeacherController.java](school_management_system/src/main/java/com/workshop/school/controller/TeacherController.java) | Handles teacher CRUD screens. |
| [school_management_system/src/main/java/com/workshop/school/controller/SubjectController.java](school_management_system/src/main/java/com/workshop/school/controller/SubjectController.java) | Handles subject CRUD screens. |
| [school_management_system/src/main/java/com/workshop/school/controller/SchoolClassController.java](school_management_system/src/main/java/com/workshop/school/controller/SchoolClassController.java) | Handles class CRUD, class teacher assignment, and subject assignment. |
| [school_management_system/src/main/resources/static/css/app.css](school_management_system/src/main/resources/static/css/app.css) | Light theme styling for all pages. |
| [school_management_system/src/main/resources/templates/login.html](school_management_system/src/main/resources/templates/login.html) | Admin login page. |
| [school_management_system/src/main/resources/templates/dashboard.html](school_management_system/src/main/resources/templates/dashboard.html) | Admin dashboard page. |

## How to Run

Start MySQL from XAMPP. XAMPP Tomcat is not required because Spring Boot uses embedded Tomcat. If XAMPP Tomcat is already using port `8080`, this project still works because it runs on port `8082`.

From the repository root:

```powershell
cd .\day_8\school_management_system
mvn spring-boot:run
```

Open:

```text
http://localhost:8082
```

Login with:

```text
Username: admin
Password: admin123
```

## What to Present

1. The project uses Spring Boot with embedded Tomcat.
2. The app uses Spring Data JPA, so tables are created from entity classes.
3. The app uses MySQL from XAMPP as the database.
4. The admin can perform CRUD operations for students, teachers, subjects, and classes.
5. The class module shows relationships by assigning one class teacher and multiple subjects.
6. The teacher form is also linked with classes, so assigning a class to a teacher updates that class teacher relationship.
7. Student and teacher assignment fields use searchable dropdowns based on already-created platform data.