<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo Signup</title>
    <link rel="stylesheet" href="assets/styles.css">
    <script src="https://unpkg.com/lucide@latest" defer></script>
    <script src="assets/todo.js" defer></script>
</head>
<body class="auth-page">
    <main class="auth-panel">
        <div class="auth-brand">
            <span class="auth-icon"><i data-lucide="sparkles"></i></span>
            <span>
                <strong>TaskFlow</strong>
                <small>College Workshop</small>
            </span>
        </div>
        <h1>Create account</h1>
        <p class="muted">Start with a clean board and keep your tasks in MySQL.</p>

        <% if (request.getAttribute("error") != null) { %>
            <div class="message error"><%= request.getAttribute("error") %></div>
        <% } %>

        <form action="auth" method="post" class="stack-form">
            <input type="hidden" name="action" value="signup">
            <label>Name
                <input type="text" name="name" required>
            </label>
            <label>Email
                <input type="email" name="email" required>
            </label>
            <label>Password
                <input type="password" name="password" minlength="4" required>
            </label>
            <button type="submit">Sign Up</button>
        </form>

        <p class="switch-link">Already registered? <a href="login.jsp">Log in</a></p>
    </main>
</body>
</html>