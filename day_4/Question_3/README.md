<!-- markdownlint-disable -->

# Question 3 - Servlet + JDBC Registration System

## Exact Question

Project Name: `FirstDynamicProject`

Tasks:

1. Create a Dynamic Web Project.
2. Create a Servlet named **Register** (`RegisterServlet.java`).
3. Create `register.html` containing:
   - Name
   - Email
   - Password
   - Gender
   - City
4. In the Servlet:
   - Read form data using `request.getParameter()`.
   - Connect to MySQL using JDBC.
   - Insert the data into a `users` table.
5. Display a success message after successful registration.
6. Use proper exception handling.

## Solution Summary

This folder contains a Maven WAR project named `FirstDynamicProject`. It works like a Dynamic Web Project and can be deployed to Apache Tomcat.

The servlet class uses `package com.workshop;`. That line is not an error: it is the Java package name for this web project. The matching folder path is `src/main/java/com/workshop/RegisterServlet.java`, so the package and folder structure are correct.

VS Code resolves the servlet imports from [../../lib/javax.servlet-api-4.0.1.jar](../../lib/javax.servlet-api-4.0.1.jar). If VS Code still shows red marks after opening the project, reload the window or run `Java: Clean Java Language Server Workspace` from the Command Palette so the Java extension reloads `.vscode/settings.json`.

The servlet uses these database settings:

| Setting | Value |
| --- | --- |
| Host | `127.0.0.1` |
| Port | `3306` |
| Database | `college_workshop` |
| Username | `root` |
| Password | empty password |

The servlet creates or updates the `users` table so it has the needed registration columns. This keeps it compatible with the earlier day 2 JDBC example if a `users` table already exists.

## Files

| File | Purpose |
| --- | --- |
| [FirstDynamicProject/pom.xml](FirstDynamicProject/pom.xml) | Maven configuration for building a WAR file with Servlet API and MySQL Connector/J. |
| [FirstDynamicProject/src/main/webapp/register.html](FirstDynamicProject/src/main/webapp/register.html) | HTML registration form with name, email, password, gender, and city fields. |
| [FirstDynamicProject/src/main/java/com/workshop/RegisterServlet.java](FirstDynamicProject/src/main/java/com/workshop/RegisterServlet.java) | Servlet mapped to `/Register`; reads form data, connects to MySQL, inserts the user, and prints a success or error page. |

## How to Run

Make sure MySQL is running and the database exists:

```sql
CREATE DATABASE IF NOT EXISTS college_workshop;
```

Build the WAR from the project folder:

```powershell
cd .\day_4\Question_3\FirstDynamicProject
mvn clean package
```

Copy the generated WAR file to Tomcat's `webapps` folder:

```powershell
copy .\target\FirstDynamicProject.war C:\apache-tomcat-9.0.XX\webapps\
```

Start Tomcat and open:

```text
http://localhost:8080/FirstDynamicProject/register.html
```