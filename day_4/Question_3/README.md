<!-- markdownlint-disable -->

# Question 3 - JSP Student Information System

## Exact Question

Project Name: `FirstDynamicProject`

Tasks:

1. Create a Dynamic Web Project using only JSP pages.
2. Create `student.jsp` with Name, Roll Number, and Submit button.
3. Submit the form to `process.jsp` using the POST method.
4. In `process.jsp`, read values using `request.getParameter()`.
5. If any field is empty, redirect to `student.jsp` using `response.sendRedirect()`.
6. If both fields are valid, store them with `request.setAttribute()` and forward to `display.jsp` using `RequestDispatcher.forward()`.
7. In `display.jsp`, include `header.jsp` and `footer.jsp` using `RequestDispatcher.include()`.
8. Display the welcome message, student name, and roll number.

## Solution Summary

This folder contains a Tomcat web project named `FirstDynamicProject`. It works like a Dynamic Web Project and uses only JSP pages for the student information flow.

## Files

| File | Purpose |
| --- | --- |
| [FirstDynamicProject/src/main/webapp/student.jsp](FirstDynamicProject/src/main/webapp/student.jsp) | JSP form that collects student name and roll number. |
| [FirstDynamicProject/src/main/webapp/process.jsp](FirstDynamicProject/src/main/webapp/process.jsp) | Reads form data, redirects empty input, and forwards valid data to `display.jsp`. |
| [FirstDynamicProject/src/main/webapp/display.jsp](FirstDynamicProject/src/main/webapp/display.jsp) | Includes header and footer, then displays the student information. |
| [FirstDynamicProject/src/main/webapp/header.jsp](FirstDynamicProject/src/main/webapp/header.jsp) | Common header message. |
| [FirstDynamicProject/src/main/webapp/footer.jsp](FirstDynamicProject/src/main/webapp/footer.jsp) | Common footer message. |

## How to Run

Create the application folder inside Tomcat:

```powershell
mkdir C:\apache-tomcat-9.0.XX\webapps\FirstDynamicProject
```

Copy the JSP files to Tomcat's `webapps` folder:

```powershell
copy .\day_4\Question_3\FirstDynamicProject\src\main\webapp\*.jsp C:\apache-tomcat-9.0.XX\webapps\FirstDynamicProject\
```

Start Tomcat and open:

```text
http://localhost:8080/FirstDynamicProject/student.jsp
```