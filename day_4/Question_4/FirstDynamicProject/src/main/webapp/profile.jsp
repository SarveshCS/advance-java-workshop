<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String email = (String) session.getAttribute("email");

    if (email == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <style>
        body {
            font-family: Georgia, "Times New Roman", serif;
            margin: 40px;
            color: #222;
        }

        .message {
            border: 1px solid #999;
            padding: 16px;
            max-width: 720px;
        }
    </style>
</head>
<body>
    <h1>WELCOME NOIDA INSTITUTE OF ENGINEERING AND TECHNOLOGY</h1>
    <div class="message">Logged in as: <%= email %></div>
    <p><a href="logout.jsp">Logout</a></p>
</body>
</html>