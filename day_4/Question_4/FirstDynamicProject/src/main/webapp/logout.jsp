<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="refresh" content="2;url=login.jsp">
    <title>Logged Out</title>
</head>
<body>
    <p>You have successfully logged out.</p>
    <p><a href="login.jsp">Back to login.jsp</a></p>
</body>
</html>