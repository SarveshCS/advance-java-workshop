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

