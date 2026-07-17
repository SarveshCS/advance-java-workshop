<!-- markdownlint-disable -->

# Question 4 - JSP Login System

## Exact Question

Project Name: `FirstDynamicProject`

Create the following JSP pages:

1. `login.jsp`
   - Email field
   - Password field
   - Login button
   - Submit using POST to `validate.jsp`
2. `validate.jsp`
   - Read email and password using `request.getParameter()`.
   - Validate against predefined credentials:
     - Email: `admin@niet.co.in`
     - Password: `12345`
   - If correct, create an HTTP Session, store the email using `session.setAttribute("email", email);`, and redirect to `profile.jsp`.
   - If incorrect, show `Invalid email or password. Please try again.` and provide a link back to `login.jsp`.
3. `profile.jsp`
   - Retrieve the user's email from the HTTP Session.
   - Display `WELCOME NOIDA INSTITUTE OF ENGINEERING AND TECHNOLOGY`.
   - Display `Logged in as: <email>`.
   - If no session exists, redirect to `login.jsp`.
4. `logout.jsp`
   - Invalidate the HTTP Session using `session.invalidate();`.
   - Display `You have successfully logged out.`.
   - Redirect back to `login.jsp`.

## Solution Summary

This folder contains a Tomcat web project named `FirstDynamicProject`. It uses only JSP pages, predefined credentials, and JSP session management. No database is needed for this question.

## Files

| File | Purpose |
| --- | --- |
| [FirstDynamicProject/src/main/webapp/login.jsp](FirstDynamicProject/src/main/webapp/login.jsp) | Login form that posts email and password to `validate.jsp`. |
| [FirstDynamicProject/src/main/webapp/validate.jsp](FirstDynamicProject/src/main/webapp/validate.jsp) | Checks the predefined credentials, creates the session, and redirects or shows an error. |
| [FirstDynamicProject/src/main/webapp/profile.jsp](FirstDynamicProject/src/main/webapp/profile.jsp) | Protected profile page that displays the welcome message and logged-in email. |
| [FirstDynamicProject/src/main/webapp/logout.jsp](FirstDynamicProject/src/main/webapp/logout.jsp) | Invalidates the session and redirects back to the login page. |

## How to Run

Create the application folder inside Tomcat:

```powershell
mkdir C:\xampp\tomcat\webapps\FirstDynamicProjectLogin
```

Copy the JSP files to Tomcat's `webapps` folder:

```powershell
copy .\day_4\Question_4\FirstDynamicProject\src\main\webapp\*.jsp C:\xampp\tomcat\webapps\FirstDynamicProjectLogin\
```

Start Tomcat and open:

```text
http://localhost:8080/FirstDynamicProjectLogin/login.jsp
```

Use these credentials:

| Field | Value |
| --- | --- |
| Email | `admin@niet.co.in` |
| Password | `12345` |