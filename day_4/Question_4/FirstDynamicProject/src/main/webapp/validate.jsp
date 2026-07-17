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
<html>
<head>
    <meta http-equiv="refresh" content="2;url=login.jsp">
    <title>Invalid Login</title>
</head>
<body>
    <p>Invalid email or password. Please try again.</p>
    <p><a href="login.jsp">Back to login.jsp</a></p>
</body>
</html>