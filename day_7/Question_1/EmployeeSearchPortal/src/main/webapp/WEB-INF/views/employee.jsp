<%@ page import="com.workshop.employee.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Employee employee = (Employee) request.getAttribute("employee");
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Details</title>
</head>
<body>
    <h2>Employee Details</h2>

    <% if (message != null) { %>
        <p><%= message %></p>
    <% } %>

    <% if (employee != null) { %>
        <p>Employee ID: <%= employee.getEmployeeId() %></p>
        <p>Name: <%= employee.getName() %></p>
        <p>Department: <%= employee.getDepartment() %></p>
        <p>Salary: <%= employee.getSalary() %></p>
    <% } %>

    <p><a href="${pageContext.request.contextPath}/">Search another employee</a></p>
</body>
</html>