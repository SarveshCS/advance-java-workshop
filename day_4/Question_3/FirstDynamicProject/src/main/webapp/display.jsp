<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RequestDispatcher header = request.getRequestDispatcher("header.jsp");
    header.include(request, response);

    String studentName = (String) request.getAttribute("studentName");
    String rollNumber = (String) request.getAttribute("rollNumber");
%>

<h2>WELCOME TO NOIDA INSTITUTE OF ENGINEERING AND TECHNOLOGY</h2>

<p>Student Name : <%= studentName %></p>

<p>Roll Number : <%= rollNumber %></p>

<%
    out.flush();
    RequestDispatcher footer = request.getRequestDispatcher("footer.jsp");
    footer.include(request, response);
%>