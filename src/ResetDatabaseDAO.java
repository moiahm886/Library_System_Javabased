import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

interface resetDB{
    void resetBooks();
    void resetUsers();
    void issued();
}
public class ResetDatabaseDAO implements resetDB{
    @Override
    public void resetBooks() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/books";
            String username = "root";
            String password = "";

            try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
                String SQL = "TRUNCATE TABLE viewbooks";
                try (PreparedStatement statement = conn.prepareStatement(SQL)) {
                    statement.executeUpdate();
                }
                System.out.println("Table viewbooks reset successfully.");
            } catch (SQLException ex) {
                throw new RuntimeException("Failed to execute SQL statement", ex);
            }
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("MySQL JDBC Driver not found", ex);
        }
    }

    @Override
    public void resetUsers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/books";
            String username = "root";
            String password = "";

            try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
                String SQL = "TRUNCATE TABLE users";
                try (PreparedStatement statement = conn.prepareStatement(SQL)) {
                    statement.executeUpdate();
                }
                System.out.println("Table users reset successfully.");
            } catch (SQLException ex) {
                throw new RuntimeException("Failed to execute SQL statement", ex);
            }
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("MySQL JDBC Driver not found", ex);
        }
    }

    @Override
    public void issued() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/books";
            String username = "root";
            String password = "";

            try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
                String SQL = "TRUNCATE TABLE issuedbooks";
                try (PreparedStatement statement = conn.prepareStatement(SQL)) {
                    statement.executeUpdate();
                }
                System.out.println("Table users reset successfully.");
            } catch (SQLException ex) {
                throw new RuntimeException("Failed to execute SQL statement", ex);
            }
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("MySQL JDBC Driver not found", ex);
        }
    }
}
