package com.workshop.todo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/college_workshop?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            throw new IllegalStateException("MySQL JDBC driver was not found.", exception);
        }
    }

    private DatabaseUtil() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void initializeDatabase() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS todo_users ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(100) NOT NULL, "
                + "email VARCHAR(150) NOT NULL UNIQUE, "
                + "password_salt VARCHAR(64) NOT NULL, "
                + "password_hash VARCHAR(128) NOT NULL, "
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")";

        String createTodosTable = "CREATE TABLE IF NOT EXISTS todo_items ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "user_id INT NOT NULL, "
                + "title VARCHAR(120) NOT NULL, "
                + "description TEXT, "
                + "due_date DATE, "
                + "completed BOOLEAN DEFAULT FALSE, "
                + "sort_order INT DEFAULT 0, "
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                + "FOREIGN KEY (user_id) REFERENCES todo_users(id) ON DELETE CASCADE"
                + ")";

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(createUsersTable);
            statement.execute(createTodosTable);
        } catch (SQLException exception) {
            throw new IllegalStateException("Could not create todo application tables.", exception);
        }
    }
}