import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcUserManagementSystem {
    private static final String TABLE_NAME = "users";
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "college_workshop";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String jdbcUrl = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, DB_USERNAME, DB_PASSWORD)) {
            ensureUsersTable(connection);
            runMenu(connection, scanner);
        } catch (SQLException exception) {
            System.out.println("Database operation failed.");
            System.out.println("Reason: " + exception.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void runMenu(Connection connection, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println();
            System.out.println("=== User Management Menu ===");
            System.out.println("1. List all users");
            System.out.println("2. Add user");
            System.out.println("3. Edit user");
            System.out.println("4. Delete user");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> listUsers(connection);
                case "2" -> addUser(connection, scanner);
                case "3" -> editUser(connection, scanner);
                case "4" -> deleteUser(connection, scanner);
                case "5" -> {
                    System.out.println("Goodbye.");
                    return;
                }
                default -> System.out.println("Please choose a valid option.");
            }
        }
    }

    private static void ensureUsersTable(Connection connection) throws SQLException {
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                + "full_name VARCHAR(100) NOT NULL, "
                + "age INT NOT NULL"
                + ")";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSql);
        }
    }

    private static void listUsers(Connection connection) throws SQLException {
        String selectSql = "SELECT id, full_name, age FROM " + TABLE_NAME + " ORDER BY id";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectSql)) {
            boolean hasRows = false;

            System.out.println();
            System.out.println("--- Users ---");
            while (resultSet.next()) {
                hasRows = true;
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("full_name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println();
            }

            if (!hasRows) {
                System.out.println("No users found.");
            }
        }
    }

    private static void addUser(Connection connection, Scanner scanner) throws SQLException {
        System.out.println();
        System.out.println("--- Add User ---");

        String fullName = readRequiredText(scanner, "Enter full name: ");
        int age = readPositiveInteger(scanner, "Enter age: ");

        String insertSql = "INSERT INTO " + TABLE_NAME + " (full_name, age) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, fullName);
            preparedStatement.setInt(2, age);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " user added successfully.");
        }
    }

    private static void editUser(Connection connection, Scanner scanner) throws SQLException {
        System.out.println();
        System.out.println("--- Edit User ---");
        int userId = readPositiveInteger(scanner, "Enter user ID to edit: ");

        String selectSql = "SELECT full_name, age FROM " + TABLE_NAME + " WHERE id = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
            selectStatement.setInt(1, userId);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (!resultSet.next()) {
                    System.out.println("User not found.");
                    return;
                }

                String currentName = resultSet.getString("full_name");
                int currentAge = resultSet.getInt("age");

                System.out.print("Enter full name [" + currentName + "]: ");
                String fullName = scanner.nextLine().trim();
                if (fullName.isEmpty()) {
                    fullName = currentName;
                }

                int age = currentAge;
                while (true) {
                    System.out.print("Enter age [" + currentAge + "]: ");
                    String input = scanner.nextLine().trim();

                    if (input.isEmpty()) {
                        break;
                    }

                    try {
                        int parsedAge = Integer.parseInt(input);
                        if (parsedAge > 0) {
                            age = parsedAge;
                            break;
                        }
                    } catch (NumberFormatException ignored) {
                        // Ask again below.
                    }

                    System.out.println("Please enter a positive whole number or press Enter to keep the current value.");
                }

                String updateSql = "UPDATE " + TABLE_NAME + " SET full_name = ?, age = ? WHERE id = ?";

                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setString(1, fullName);
                    updateStatement.setInt(2, age);
                    updateStatement.setInt(3, userId);

                    int affectedRows = updateStatement.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("User updated successfully.");
                    } else {
                        System.out.println("No changes were made.");
                    }
                }
            }
        }
    }

    private static void deleteUser(Connection connection, Scanner scanner) throws SQLException {
        System.out.println();
        System.out.println("--- Delete User ---");
        int userId = readPositiveInteger(scanner, "Enter user ID to delete: ");

        String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, userId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found.");
            }
        }
    }

    private static String readRequiredText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Value cannot be empty.");
        }
    }

    private static int readPositiveInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Try again below.
            }

            System.out.println("Please enter a positive whole number.");
        }
    }
}