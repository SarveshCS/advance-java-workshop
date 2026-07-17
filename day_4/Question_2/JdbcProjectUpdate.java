import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcProjectUpdate {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/NIET";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            createStudentTable(connection);
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
        String createTableSql = "CREATE TABLE IF NOT EXISTS STUDENT ("
                + "ID INT PRIMARY KEY AUTO_INCREMENT, "
                + "Name VARCHAR(50), "
                + "Age INT, "
                + "Department VARCHAR(50), "
                + "City VARCHAR(50)"
                + ")";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSql);
        }
    }

    private static void updateStudent(Connection connection) throws SQLException {
        String updateSql = "UPDATE STUDENT SET Age = ?, Department = ? WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setInt(1, 23);
            preparedStatement.setString(2, "Artificial Intelligence");
            preparedStatement.setInt(3, 1);
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " student record updated.");
        }
    }

    private static void deleteStudent(Connection connection) throws SQLException {
        String deleteSql = "DELETE FROM STUDENT WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, 2);
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " student record deleted.");
        }
    }

    private static void insertNewStudents(Connection connection) throws SQLException {
        String insertSql = "INSERT INTO STUDENT (Name, Age, Department, City) VALUES (?, ?, ?, ?)";
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

    private static void displayStudents(Connection connection) throws SQLException {
        String selectSql = "SELECT ID, Name, Age, Department, City FROM STUDENT";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectSql)) {
            System.out.println();
            System.out.println("UPDATED STUDENT RECORDS");
            System.out.printf("%-5s %-20s %-5s %-24s %-15s%n", "ID", "Name", "Age", "Department", "City");

            while (resultSet.next()) {
                System.out.printf(
                        "%-5d %-20s %-5d %-24s %-15s%n",
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getInt("Age"),
                        resultSet.getString("Department"),
                        resultSet.getString("City"));
            }
        }
    }
}