import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcProject {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/NIET";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            createStudentTable(connection);
            insertSampleStudents(connection);
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
            System.out.println("STUDENT table created successfully.");
        }
    }

    private static void insertSampleStudents(Connection connection) throws SQLException {
        String insertSql = "INSERT INTO STUDENT (Name, Age, Department, City) VALUES (?, ?, ?, ?)";

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
            System.out.println("10 sample student records inserted successfully.");
        }
    }

    private static void displayStudents(Connection connection) throws SQLException {
        String selectSql = "SELECT ID, Name, Age, Department, City FROM STUDENT";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectSql)) {
            System.out.println();
            System.out.println("STUDENT RECORDS");
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