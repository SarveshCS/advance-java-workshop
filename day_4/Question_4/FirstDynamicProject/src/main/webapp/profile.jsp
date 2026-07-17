<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String email = (String) session.getAttribute("email");

    if (email == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <h1>WELCOME NOIDA INSTITUTE OF ENGINEERING AND TECHNOLOGY</h1>
    <p>Logged in as: <%= email %></p>
    <p><a href="logout.jsp">Logout</a></p>
</body>
</html>