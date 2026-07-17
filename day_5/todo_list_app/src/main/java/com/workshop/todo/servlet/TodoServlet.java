package com.workshop.todo.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.workshop.todo.dao.TodoDao;

@WebServlet("/todo/*")
public class TodoServlet extends HttpServlet {
    private final TodoDao todoDao = new TodoDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        String path = request.getPathInfo();

        try {
            if ("/add".equals(path)) {
                addTodo(request, userId);
            } else if ("/update".equals(path)) {
                updateTodo(request, userId);
                if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    return;
                }
            } else if ("/toggle".equals(path)) {
                todoDao.toggleComplete(userId, Integer.parseInt(request.getParameter("id")));
                if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    return;
                }
            } else if ("/delete".equals(path)) {
                todoDao.deleteTodo(userId, Integer.parseInt(request.getParameter("id")));
                if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    return;
                }
            } else if ("/reorder".equals(path)) {
                todoDao.reorderTodos(userId, request.getParameterValues("todoId"));
                if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    return;
                }
            }
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } catch (NumberFormatException exception) {
            response.sendRedirect(request.getContextPath() + "/dashboard?error=bad-id");
        } catch (SQLException exception) {
            throw new ServletException("Todo action failed.", exception);
        }
    }

    private void addTodo(HttpServletRequest request, int userId) throws SQLException {
        String title = clean(request.getParameter("title"));
        String description = clean(request.getParameter("description"));
        String dueDateText = clean(request.getParameter("dueDate"));

        if (title.isEmpty()) {
            return;
        }

        Date dueDate = dueDateText.isEmpty() ? null : Date.valueOf(dueDateText);
        todoDao.addTodo(userId, title, description, dueDate);
    }

    private void updateTodo(HttpServletRequest request, int userId) throws SQLException {
        int todoId = Integer.parseInt(request.getParameter("id"));
        String title = clean(request.getParameter("title"));
        String description = clean(request.getParameter("description"));
        String dueDateText = clean(request.getParameter("dueDate"));

        if (title.isEmpty()) {
            return;
        }

        Date dueDate = dueDateText.isEmpty() ? null : Date.valueOf(dueDateText);
        todoDao.updateTodo(userId, todoId, title, description, dueDate);
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}