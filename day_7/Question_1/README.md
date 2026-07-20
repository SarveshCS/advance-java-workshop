<!-- markdownlint-disable -->

# Question 1 - Employee Search Portal Using Spring MVC

## Exact Question

Develop an Employee Search Portal using Spring MVC.

Tasks:

1. Create `search.jsp` with Employee ID and Search button.
2. Create `EmployeeController` to receive Employee ID using `@RequestParam`.
3. Invoke `EmployeeDAO` from the controller.
4. If employee exists, display employee details on `employee.jsp`.
5. Otherwise, display `Employee not found.`.
6. Create an `Employee` POJO with Employee ID, Name, Department, and Salary.
7. Create `EmployeeDAO` using `JdbcTemplate` to fetch data from the `employee` table.
8. Configure Spring MVC, Spring JDBC, DataSource, JdbcTemplate, and View Resolver.
9. Use PreparedStatement internally through `JdbcTemplate` and handle exceptions properly.

## Solution Summary

This folder contains a Spring MVC web application named `EmployeeSearchPortal`. It uses Spring MVC for request handling, JSP pages for the view, Spring JDBC `JdbcTemplate` for database access, and MySQL database `NIET` with the same local credentials used in earlier JDBC examples.

| Setting | Value |
| --- | --- |
| Host | `localhost` |
| Port | `3306` |
| Database | `NIET` |
| Username | `root` |
| Password | empty password |

## Files

| File | Purpose |
| --- | --- |
| [EmployeeSearchPortal/pom.xml](EmployeeSearchPortal/pom.xml) | Maven WAR project file with Spring MVC, Spring JDBC, Servlet API, and MySQL dependencies. |
| [EmployeeSearchPortal/src/main/java/com/workshop/employee/controller/EmployeeController.java](EmployeeSearchPortal/src/main/java/com/workshop/employee/controller/EmployeeController.java) | Receives the employee ID, calls the DAO, and sends data or a not-found message to the view. |
| [EmployeeSearchPortal/src/main/java/com/workshop/employee/dao/EmployeeDAO.java](EmployeeSearchPortal/src/main/java/com/workshop/employee/dao/EmployeeDAO.java) | Uses `JdbcTemplate` to search the `employee` table by employee ID. |
| [EmployeeSearchPortal/src/main/java/com/workshop/employee/model/Employee.java](EmployeeSearchPortal/src/main/java/com/workshop/employee/model/Employee.java) | POJO class for employee details. |
| [EmployeeSearchPortal/src/main/webapp/WEB-INF/dispatcher-servlet.xml](EmployeeSearchPortal/src/main/webapp/WEB-INF/dispatcher-servlet.xml) | Configures Spring MVC, DataSource, JdbcTemplate, EmployeeDAO, and View Resolver. |
| [EmployeeSearchPortal/src/main/webapp/WEB-INF/web.xml](EmployeeSearchPortal/src/main/webapp/WEB-INF/web.xml) | Configures the Spring DispatcherServlet. |
| [EmployeeSearchPortal/src/main/webapp/WEB-INF/views/search.jsp](EmployeeSearchPortal/src/main/webapp/WEB-INF/views/search.jsp) | Search form for entering Employee ID. |
| [EmployeeSearchPortal/src/main/webapp/WEB-INF/views/employee.jsp](EmployeeSearchPortal/src/main/webapp/WEB-INF/views/employee.jsp) | Displays employee details or the not-found message. |
| [EmployeeSearchPortal/database.sql](EmployeeSearchPortal/database.sql) | Creates the database table and inserts sample employee records. |

## How to Run

Start MySQL from XAMPP, then run this SQL in phpMyAdmin or MySQL command line:

```sql
source day_7/Question_1/EmployeeSearchPortal/database.sql;
```

Build the WAR file from the repository root:

```powershell
cd .\day_7\Question_1\EmployeeSearchPortal
mvn clean package
```

Copy the generated WAR file to Tomcat in XAMPP:

```powershell
copy .\target\EmployeeSearchPortal.war C:\xampp\tomcat\webapps\
```

Start Tomcat from XAMPP and open:

```text
http://localhost:8080/EmployeeSearchPortal/
```

Try these employee IDs:

| Employee ID | Name |
| --- | --- |
| `101` | Aarav Sharma |
| `102` | Ananya Gupta |
| `103` | Rohan Verma |

Try any other number to see:

```text
Employee not found.
```