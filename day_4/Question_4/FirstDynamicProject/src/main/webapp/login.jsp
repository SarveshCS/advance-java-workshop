<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Georgia, "Times New Roman", serif;
            margin: 40px;
            color: #222;
        }

        label,
        input,
        button {
            display: block;
            margin-top: 12px;
        }

        input {
            padding: 8px;
            width: 280px;
        }

        button {
            padding: 8px 14px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Login</h1>

    <form action="validate.jsp" method="post">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Login</button>
    </form>
</body>
</html>