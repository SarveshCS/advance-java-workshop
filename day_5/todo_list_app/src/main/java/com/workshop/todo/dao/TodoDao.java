package com.workshop.todo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.workshop.todo.db.DatabaseUtil;
import com.workshop.todo.model.TodoItem;

public class TodoDao {
    public List<TodoItem> findByUser(int userId, boolean hideCompleted, String sortBy) throws SQLException {
        StringBuilder selectSql = new StringBuilder("SELECT id, user_id, title, description, due_date, completed, sort_order FROM todo_items WHERE user_id = ?");
        if (hideCompleted) {
            selectSql.append(" AND completed = FALSE");
        }

        if ("dueDate".equals(sortBy)) {
            selectSql.append(" ORDER BY due_date IS NULL, due_date, sort_order, id");
        } else if ("title".equals(sortBy)) {
            selectSql.append(" ORDER BY title, sort_order, id");
        } else {
            selectSql.append(" ORDER BY sort_order, id");
        }

        List<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectSql.toString())) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    todoItems.add(mapTodoItem(resultSet));
                }
            }
        }

        return todoItems;
    }

    public void addTodo(int userId, String title, String description, Date dueDate) throws SQLException {
        String insertSql = "INSERT INTO todo_items (user_id, title, description, due_date, sort_order) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.setDate(4, dueDate);
            preparedStatement.setInt(5, getNextSortOrder(connection, userId));
            preparedStatement.executeUpdate();
        }
    }

    public void updateTodo(int userId, int todoId, String title, String description, Date dueDate) throws SQLException {
        String updateSql = "UPDATE todo_items SET title = ?, description = ?, due_date = ? WHERE id = ? AND user_id = ?";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setDate(3, dueDate);
            preparedStatement.setInt(4, todoId);
            preparedStatement.setInt(5, userId);
            preparedStatement.executeUpdate();
        }
    }

    public int countTodos(int userId) throws SQLException {
        return countTodos(userId, null);
    }

    public int countTodos(int userId, Boolean completed) throws SQLException {
        StringBuilder selectSql = new StringBuilder("SELECT COUNT(*) AS total_count FROM todo_items WHERE user_id = ?");
        if (completed != null) {
            selectSql.append(" AND completed = ?");
        }

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectSql.toString())) {
            preparedStatement.setInt(1, userId);
            if (completed != null) {
                preparedStatement.setBoolean(2, completed);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_count");
                }
            }
        }

        return 0;
    }

    public void toggleComplete(int userId, int todoId) throws SQLException {
        String updateSql = "UPDATE todo_items SET completed = NOT completed WHERE id = ? AND user_id = ?";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setInt(1, todoId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteTodo(int userId, int todoId) throws SQLException {
        String deleteSql = "DELETE FROM todo_items WHERE id = ? AND user_id = ?";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, todoId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        }
    }

    public void reorderTodos(int userId, String[] todoIds) throws SQLException {
        if (todoIds == null) {
            return;
        }

        String updateSql = "UPDATE todo_items SET sort_order = ? WHERE id = ? AND user_id = ?";
        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            connection.setAutoCommit(false);
            for (int index = 0; index < todoIds.length; index++) {
                preparedStatement.setInt(1, index + 1);
                preparedStatement.setInt(2, Integer.parseInt(todoIds[index]));
                preparedStatement.setInt(3, userId);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        }
    }

    private int getNextSortOrder(Connection connection, int userId) throws SQLException {
        String selectSql = "SELECT COALESCE(MAX(sort_order), 0) + 1 AS next_sort_order FROM todo_items WHERE user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("next_sort_order");
                }
            }
        }

        return 1;
    }

    private TodoItem mapTodoItem(ResultSet resultSet) throws SQLException {
        TodoItem todoItem = new TodoItem();
        todoItem.setId(resultSet.getInt("id"));
        todoItem.setUserId(resultSet.getInt("user_id"));
        todoItem.setTitle(resultSet.getString("title"));
        todoItem.setDescription(resultSet.getString("description"));
        Date dueDate = resultSet.getDate("due_date");
        if (dueDate != null) {
            todoItem.setDueDate(dueDate.toLocalDate());
        }
        todoItem.setCompleted(resultSet.getBoolean("completed"));
        todoItem.setSortOrder(resultSet.getInt("sort_order"));
        return todoItem;
    }
}