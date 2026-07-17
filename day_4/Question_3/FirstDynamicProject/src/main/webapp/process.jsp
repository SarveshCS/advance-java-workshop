<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String studentName = request.getParameter("studentName");
    String rollNumber = request.getParameter("rollNumber");

    if (studentName == null || studentName.trim().isEmpty()
            || rollNumber == null || rollNumber.trim().isEmpty()) {
        response.sendRedirect("student.jsp");
    } else {
        request.setAttribute("studentName", studentName.trim());
        request.setAttribute("rollNumber", rollNumber.trim());

        RequestDispatcher dispatcher = request.getRequestDispatcher("display.jsp");
        dispatcher.forward(request, response);
    }
%>