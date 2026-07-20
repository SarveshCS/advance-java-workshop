<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Search Portal</title>
</head>
<body>
    <h2>Employee Search Portal</h2>

    <form action="search" method="post">
        <label>Employee ID:</label>
        <input type="number" name="employeeId" required>
        <br><br>

        <input type="submit" value="Search">
    </form>
</body>
</html>