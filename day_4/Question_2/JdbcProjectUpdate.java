import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcProjectUpdate {
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "college_workshop";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    private static final String TABLE_NAME = "STUDENT";

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, DB_USERNAME, DB_PASSWORD)) {
            createStudentTable(connection);
            insertInitialRecordsIfNeeded(connection);
            updateStudent(connection);
            deleteStudent(connection);
            insertNewStudents(connection);
            displayStudents(connection);
        } catch (SQLException exception) {
            System.out.println("Database operation failed.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }

    private static void createStudentTable(Connection connection) throws SQLException {
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "age INT NOT NULL, "
                + "department VARCHAR(80) NOT NULL, "
                + "city VARCHAR(80) NOT NULL"
                + ")";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSql);
        }
    }

    private static void insertInitialRecordsIfNeeded(Connection connection) throws SQLException {
        String countSql = "SELECT COUNT(*) FROM " + TABLE_NAME;

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(countSql)) {
            resultSet.next();
            if (resultSet.getInt(1) > 0) {
                return;
            }
        }

        String insertSql = "INSERT INTO " + TABLE_NAME + " (name, age, department, city) VALUES (?, ?, ?, ?)";
        Object[][] students = {
                {"Aarav Sharma", 20, "Computer Science", "Noida"},
                {"Ananya Gupta", 21, "Information Technology", "Delhi"},
                {"Rohan Verma", 22, "Electronics", "Ghaziabad"},
                {"Priya Singh", 20, "Mechanical", "Lucknow"},
                {"Kunal Mehta", 23, "Civil", "Jaipur"},
                {"Neha Yadav", 21, "Computer Science", "Gurugram"},
                {"Vivek Kumar", 22, "Information Technology", "Meerut"},
                {"Isha Saxena", 20, "Electronics", "Kanpur"},
                {"Sahil Khan", 24, "Mechanical", "Agra"},
                {"Meera Joshi", 21, "Civil", "Dehradun"}
        };

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            for (Object[] student : students) {
                preparedStatement.setString(1, (String) student[0]);
                preparedStatement.setInt(2, (Integer) student[1]);
                preparedStatement.setString(3, (String) student[2]);
                preparedStatement.setString(4, (String) student[3]);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    private static void updateStudent(Connection connection) throws SQLException {
        int studentId = findFirstStudentId(connection);
        if (studentId == -1) {
            System.out.println("No student record is available for update.");
            return;
        }

        String updateSql = "UPDATE " + TABLE_NAME + " SET department = ?, city = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, "Artificial Intelligence");
            preparedStatement.setString(2, "Greater Noida");
            preparedStatement.setInt(3, studentId);
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " student record updated.");
        }
    }

    private static void deleteStudent(Connection connection) throws SQLException {
        int studentId = findSecondStudentId(connection);
        if (studentId == -1) {
            System.out.println("No second student record is available for delete operation.");
            return;
        }

        String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, studentId);
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " student record deleted.");
        }
    }

    private static void insertNewStudents(Connection connection) throws SQLException {
        String insertSql = "INSERT INTO " + TABLE_NAME + " (name, age, department, city) VALUES (?, ?, ?, ?)";
        Object[][] students = {
                {"Tanya Bansal", 22, "Data Science", "Noida"},
                {"Aditya Raj", 23, "Cyber Security", "Delhi"}
        };

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            for (Object[] student : students) {
                preparedStatement.setString(1, (String) student[0]);
                preparedStatement.setInt(2, (Integer) student[1]);
                preparedStatement.setString(3, (String) student[2]);
                preparedStatement.setString(4, (String) student[3]);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("2 new student records inserted.");
        }
    }

    private static int findFirstStudentId(Connection connection) throws SQLException {
        String selectSql = "SELECT id FROM " + TABLE_NAME + " ORDER BY id LIMIT 1";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectSql)) {
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return -1;
        }
    }

    private static int findSecondStudentId(Connection connection) throws SQLException {
        String selectSql = "SELECT id FROM " + TABLE_NAME + " ORDER BY id LIMIT 1 OFFSET 1";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectSql)) {
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return -1;
        }
    }

    private static void displayStudents(Connection connection) throws SQLException {
        String selectSql = "SELECT id, name, age, department, city FROM " + TABLE_NAME + " ORDER BY id";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectSql)) {
            System.out.println();
            System.out.println("------------- UPDATED STUDENT RECORDS -------------");
            System.out.printf("%-5s %-20s %-5s %-24s %-15s%n", "ID", "Name", "Age", "Department", "City");
            System.out.println("---------------------------------------------------");

            while (resultSet.next()) {
                System.out.printf(
                        "%-5d %-20s %-5d %-24s %-15s%n",
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("department"),
                        resultSet.getString("city"));
            }
        }
    }
}