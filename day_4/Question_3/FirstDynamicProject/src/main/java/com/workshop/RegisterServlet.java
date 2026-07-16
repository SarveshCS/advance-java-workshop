package com.workshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "college_workshop";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    private static final String TABLE_NAME = "users";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = readParameter(request, "name");
        String email = readParameter(request, "email");
        String password = readParameter(request, "password");
        String gender = readParameter(request, "gender");
        String city = readParameter(request, "city");

        try (PrintWriter out = response.getWriter()) {
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || gender.isEmpty() || city.isEmpty()) {
                printPage(out, "Registration Failed", "All fields are required.", "register.html");
                return;
            }

            try (Connection connection = getConnection()) {
                ensureUsersTable(connection);
                insertUser(connection, name, email, password, gender, city);
                printPage(out, "Registration Successful", "User registered successfully.", "register.html");
            } catch (SQLException exception) {
                printPage(out, "Registration Failed", "Reason: " + escapeHtml(exception.getMessage()), "register.html");
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        return DriverManager.getConnection(jdbcUrl, DB_USERNAME, DB_PASSWORD);
    }

    private static void ensureUsersTable(Connection connection) throws SQLException {
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "email VARCHAR(120) NOT NULL, "
                + "password VARCHAR(100) NOT NULL, "
                + "gender VARCHAR(20) NOT NULL, "
                + "city VARCHAR(80) NOT NULL"
                + ")";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSql);
        }

        addColumnIfMissing(connection, "name", "VARCHAR(100) NOT NULL DEFAULT ''");
        addColumnIfMissing(connection, "email", "VARCHAR(120) NOT NULL DEFAULT ''");
        addColumnIfMissing(connection, "password", "VARCHAR(100) NOT NULL DEFAULT ''");
        addColumnIfMissing(connection, "gender", "VARCHAR(20) NOT NULL DEFAULT ''");
        addColumnIfMissing(connection, "city", "VARCHAR(80) NOT NULL DEFAULT ''");
    }

    private static void addColumnIfMissing(Connection connection, String columnName, String definition) throws SQLException {
        if (hasColumn(connection, columnName)) {
            return;
        }

        String alterSql = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + columnName + " " + definition;
        try (Statement statement = connection.createStatement()) {
            statement.execute(alterSql);
        }
    }

    private static boolean hasColumn(Connection connection, String columnName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getColumns(null, null, TABLE_NAME, columnName)) {
            return resultSet.next();
        }
    }

    private static void insertUser(Connection connection, String name, String email, String password, String gender, String city)
            throws SQLException {
        List<String> columns = new ArrayList<>();
        List<String> placeholders = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        addValue(columns, placeholders, values, "name", name);
        addValue(columns, placeholders, values, "email", email);
        addValue(columns, placeholders, values, "password", password);
        addValue(columns, placeholders, values, "gender", gender);
        addValue(columns, placeholders, values, "city", city);

        if (hasColumn(connection, "full_name")) {
            addValue(columns, placeholders, values, "full_name", name);
        }
        if (hasColumn(connection, "age")) {
            addValue(columns, placeholders, values, "age", 0);
        }

        String insertSql = "INSERT INTO " + TABLE_NAME + " (" + String.join(", ", columns) + ") VALUES ("
                + String.join(", ", placeholders) + ")";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            for (int index = 0; index < values.size(); index++) {
                preparedStatement.setObject(index + 1, values.get(index));
            }
            preparedStatement.executeUpdate();
        }
    }

    private static void addValue(List<String> columns, List<String> placeholders, List<Object> values,
            String columnName, Object value) {
        columns.add(columnName);
        placeholders.add("?");
        values.add(value);
    }

    private static String readParameter(HttpServletRequest request, String parameterName) {
        String value = request.getParameter(parameterName);
        return value == null ? "" : value.trim();
    }

    private static void printPage(PrintWriter out, String title, String message, String link) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>" + escapeHtml(title) + "</title>");
        out.println("<style>body{font-family:Georgia,'Times New Roman',serif;margin:40px;color:#222;} .message{border:1px solid #999;padding:16px;max-width:620px;} a{display:inline-block;margin-top:16px;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + escapeHtml(title) + "</h1>");
        out.println("<div class=\"message\">" + message + "</div>");
        out.println("<a href=\"" + escapeHtml(link) + "\">Back to registration form</a>");
        out.println("</body>");
        out.println("</html>");
    }

    private static String escapeHtml(String value) {
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}