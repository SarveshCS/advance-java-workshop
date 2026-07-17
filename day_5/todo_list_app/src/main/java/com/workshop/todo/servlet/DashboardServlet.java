package com.workshop.todo.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.workshop.todo.dao.TodoDao;
import com.workshop.todo.model.TodoItem;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private final TodoDao todoDao = new TodoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        boolean hideCompleted = "true".equals(request.getParameter("hideCompleted"));
        String sortBy = request.getParameter("sortBy");

        try {
            List<TodoItem> todoItems = todoDao.findByUser(userId, hideCompleted, sortBy);
            int totalCount = todoDao.countTodos(userId);
            int completedCount = todoDao.countTodos(userId, true);
            request.setAttribute("todoItems", todoItems);
            request.setAttribute("totalCount", totalCount);
            request.setAttribute("completedCount", completedCount);
            request.setAttribute("pendingCount", totalCount - completedCount);
            request.setAttribute("hideCompleted", hideCompleted);
            request.setAttribute("sortBy", sortBy == null ? "manual" : sortBy);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (SQLException exception) {
            throw new ServletException("Could not load todo dashboard.", exception);
        }
    }
}