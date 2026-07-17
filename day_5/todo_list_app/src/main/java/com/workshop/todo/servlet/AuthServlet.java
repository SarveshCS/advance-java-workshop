package com.workshop.todo.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.workshop.todo.dao.UserDao;
import com.workshop.todo.model.User;
import com.workshop.todo.util.PasswordUtil;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("signup".equals(action)) {
            signUp(request, response);
        } else {
            logIn(request, response);
        }
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = clean(request.getParameter("name"));
        String email = clean(request.getParameter("email")).toLowerCase();
        String password = request.getParameter("password");

        if (name.isEmpty() || email.isEmpty() || password == null || password.length() < 4) {
            request.setAttribute("error", "Enter name, email, and a password with at least 4 characters.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        try {
            userDao.createUser(name, email, password);
            response.sendRedirect("login.jsp?registered=1");
        } catch (SQLIntegrityConstraintViolationException exception) {
            request.setAttribute("error", "This email is already registered. Try logging in.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } catch (SQLException exception) {
            throw new ServletException("Signup failed.", exception);
        }
    }

    private void logIn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = clean(request.getParameter("email")).toLowerCase();
        String password = request.getParameter("password");

        try {
            User user = userDao.findByEmail(email);
            if (user == null || password == null || !PasswordUtil.matches(password, user.getPasswordSalt(), user.getPasswordHash())) {
                request.setAttribute("error", "Invalid email or password. Please try again.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userEmail", user.getEmail());
            response.sendRedirect("dashboard");
        } catch (SQLException exception) {
            throw new ServletException("Login failed.", exception);
        }
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}