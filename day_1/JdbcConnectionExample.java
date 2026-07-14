import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcConnectionExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter database host or IP address: ");
        String host = scanner.nextLine();

        System.out.print("Enter database port: ");
        String port = scanner.nextLine();

        System.out.print("Enter database name: ");
        String databaseName = scanner.nextLine();

        System.out.print("Enter database username: ");
        String username = scanner.nextLine();

        System.out.print("Enter database password: ");
        String password = scanner.nextLine();

        String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + databaseName;

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Database connected successfully.");
        } catch (SQLException exception) {
            System.out.println("Database connection failed.");
            System.out.println("Reason: " + exception.getMessage());
        }

        scanner.close();
    }
}