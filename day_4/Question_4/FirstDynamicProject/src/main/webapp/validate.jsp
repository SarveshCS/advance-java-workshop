<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if ("admin@niet.co.in".equals(email) && "12345".equals(password)) {
        session.setAttribute("email", email);
        response.sendRedirect("profile.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Invalid Login</title>
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
    <h1>Login Failed</h1>
    <div class="message">Invalid email or password. Please try again.</div>
    <p><a href="login.jsp">Back to login.jsp</a></p>
</body>
</html>