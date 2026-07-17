<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Information</title>
</head>
<body>
    <h2>Student Information System</h2>

    <form action="process.jsp" method="post">
        <label>Name:</label>
        <input type="text" name="studentName">
        <br><br>

        <label>Roll Number:</label>
        <input type="text" name="rollNumber">
        <br><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>