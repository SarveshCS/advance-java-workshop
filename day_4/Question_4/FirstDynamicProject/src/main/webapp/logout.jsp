<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="refresh" content="2;url=login.jsp">
    <title>Logged Out</title>
    <style>
        body {
            font-family: Georgia, "Times New Roman", serif;
            margin: 40px;
            color: #222;
        }

        .message {
            border: 1px solid #999;
            padding: 16px;
            max-width: 620px;
        }
    </style>
</head>
<body>
    <h1>Logout</h1>
    <div class="message">You have successfully logged out.</div>
    <p><a href="login.jsp">Back to login.jsp</a></p>
</body>
</html>