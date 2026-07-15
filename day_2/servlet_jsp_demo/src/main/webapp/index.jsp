<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Servlet and JSP Demo</title>
    <style>
        body {
            font-family: Georgia, "Times New Roman", serif;
            margin: 40px;
            color: #222;
        }

        label,
        input,
        button {
            display: block;
            margin-top: 12px;
        }

        input {
            padding: 8px;
            width: 260px;
        }

        button {
            padding: 8px 14px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Servlet and JSP Demo</h1>
    <p>Enter your name. The form will go to a Servlet, and the Servlet will forward the result to a JSP page.</p>

    <form action="hello" method="post">
        <label for="studentName">Student name</label>
        <input type="text" id="studentName" name="studentName" placeholder="Example: Sarvesh">
        <button type="submit">Send to Servlet</button>
    </form>
</body>
</html>