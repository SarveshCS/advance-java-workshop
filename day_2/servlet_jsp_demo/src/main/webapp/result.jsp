<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Servlet Result</title>
    <style>
        body {
            font-family: Georgia, "Times New Roman", serif;
            margin: 40px;
            color: #222;
        }

        .message {
            margin-top: 20px;
            padding: 16px;
            border: 1px solid #999;
            max-width: 620px;
        }
    </style>
</head>
<body>
    <h1>Result Page</h1>
    <div class="message">
        ${message}
    </div>
    <p><a href="index.jsp">Back to form</a></p>
</body>
</html>