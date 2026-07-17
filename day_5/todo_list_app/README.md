<!-- markdownlint-disable -->

# Day 5 - Todo List Dynamic Web Project

## Project Summary

This project is a simple Servlet, JSP, JDBC, and MySQL todo list application for Tomcat. A student can sign up, log in, create todo items, edit existing todo details and due dates, mark items complete with one click, hide completed items, switch between light and dark themes, and drag todo rows to save a custom order automatically.

The application uses the same local database style as the Day 4 JDBC examples:

| Setting | Value |
| --- | --- |
| Database | `college_workshop` |
| MySQL user | `root` |
| MySQL password | empty password |
| JDBC URL | `jdbc:mysql://localhost:3306/college_workshop?createDatabaseIfNotExist=true` |

The code automatically creates these tables when Tomcat starts the application:

| Table | Purpose |
| --- | --- |
| `todo_users` | Stores signup/login details. |
| `todo_items` | Stores todo items for each logged-in user. |

## Files

| File | Purpose |
| --- | --- |
| [pom.xml](pom.xml) | Maven WAR configuration for Tomcat. |
| [src/main/java/com/workshop/todo/db/DatabaseUtil.java](src/main/java/com/workshop/todo/db/DatabaseUtil.java) | Opens MySQL connections and creates required tables. |
| [src/main/java/com/workshop/todo/listener/AppStartupListener.java](src/main/java/com/workshop/todo/listener/AppStartupListener.java) | Runs database setup when the web app starts. |
| [src/main/java/com/workshop/todo/servlet/AuthServlet.java](src/main/java/com/workshop/todo/servlet/AuthServlet.java) | Handles signup and login form submissions. |
| [src/main/java/com/workshop/todo/servlet/DashboardServlet.java](src/main/java/com/workshop/todo/servlet/DashboardServlet.java) | Loads the protected todo dashboard. |
| [src/main/java/com/workshop/todo/servlet/TodoServlet.java](src/main/java/com/workshop/todo/servlet/TodoServlet.java) | Adds, edits, completes, deletes, and auto-reorders todo items. |
| [src/main/webapp/login.jsp](src/main/webapp/login.jsp) | Login page. |
| [src/main/webapp/signup.jsp](src/main/webapp/signup.jsp) | Signup page. |
| [src/main/webapp/dashboard.jsp](src/main/webapp/dashboard.jsp) | Modern todo dashboard with analytics, icons, editable tasks, filters, sorting, and bottom task composer. |

## Build

If Maven is installed, run this from the project folder:

```powershell
cd .\day_5\todo_list_app
mvn clean package
```

If Maven is not installed, use the manual commands below from the repository root:

```powershell
cd .\day_5\todo_list_app
mkdir .\target\classes -Force
$sources = Get-ChildItem -Recurse .\src\main\java\*.java | ForEach-Object { $_.FullName }
& 'C:\Program Files\Common Files\Oracle\Java\javapath\javac.exe' -cp '..\..\lib\javax.servlet-api-4.0.1.jar;..\..\lib\mysql-connector-j-8.4.0.jar' -d .\target\classes $sources
mkdir .\target\todo-list-app\WEB-INF\classes -Force
mkdir .\target\todo-list-app\WEB-INF\lib -Force
copy .\src\main\webapp\* .\target\todo-list-app\ -Recurse -Force
copy .\target\classes\* .\target\todo-list-app\WEB-INF\classes\ -Recurse -Force
copy ..\..\lib\mysql-connector-j-8.4.0.jar .\target\todo-list-app\WEB-INF\lib\ -Force
cd .\target\todo-list-app
Compress-Archive -Path .\* -DestinationPath ..\todo-list-app.zip -Force
Rename-Item ..\todo-list-app.zip todo-list-app.war
```

## Copy to XAMPP Tomcat

After building, copy the WAR file into Tomcat's `webapps` folder:

```powershell
copy .\target\todo-list-app.war C:\xampp\tomcat\webapps\todo-list-app.war
```

Start or restart Tomcat, then open:

```text
http://localhost:8080/todo-list-app/
```

Use the signup page to create a new account, then log in and manage todo items.