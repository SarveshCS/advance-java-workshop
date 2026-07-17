package com.workshop.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.workshop.todo.db.DatabaseUtil;
import com.workshop.todo.model.User;
import com.workshop.todo.util.PasswordUtil;

public class UserDao {
    public boolean createUser(String name, String email, String password) throws SQLException {
        String salt = PasswordUtil.createSalt();
        String passwordHash = PasswordUtil.hashPassword(password, salt);
        String insertSql = "INSERT INTO todo_users (name, email, password_salt, password_hash) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, salt);
            preparedStatement.setString(4, passwordHash);
            return preparedStatement.executeUpdate() == 1;
        }
    }

    public User findByEmail(String email) throws SQLException {
        String selectSql = "SELECT id, name, email, password_salt, password_hash FROM todo_users WHERE email = ?";

        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPasswordSalt(resultSet.getString("password_salt"));
                    user.setPasswordHash(resultSet.getString("password_hash"));
                    return user;
                }
            }
        }

        return null;
    }
}