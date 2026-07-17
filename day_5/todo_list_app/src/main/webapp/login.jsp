<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo Login</title>
    <link rel="stylesheet" href="assets/styles.css">
    <script src="https://unpkg.com/lucide@latest" defer></script>
    <script src="assets/todo.js" defer></script>
</head>
<body class="auth-page">
    <main class="auth-panel">
        <div class="auth-brand">
            <span class="auth-icon"><i data-lucide="list-checks"></i></span>
            <span>
                <strong>TaskFlow</strong>
                <small>College Workshop</small>
            </span>
        </div>
        <h1>Welcome back</h1>
        <p class="muted">Sign in and continue your task board.</p>

        <% if (request.getAttribute("error") != null) { %>
            <div class="message error"><%= request.getAttribute("error") %></div>
        <% } %>
        <% if ("1".equals(request.getParameter("registered"))) { %>
            <div class="message success">Signup complete. Please log in.</div>
        <% } %>
        <% if ("1".equals(request.getParameter("logout"))) { %>
            <div class="message success">You have successfully logged out.</div>
        <% } %>

        <form action="auth" method="post" class="stack-form">
            <input type="hidden" name="action" value="login">
            <label>Email
                <input type="email" name="email" required>
            </label>
            <label>Password
                <input type="password" name="password" required>
            </label>
            <button type="submit">Log In</button>
        </form>

        <p class="switch-link">New user? <a href="signup.jsp">Create an account</a></p>
    </main>
</body>
</html>