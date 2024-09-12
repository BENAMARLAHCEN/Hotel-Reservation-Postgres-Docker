package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // JDBC URL for connecting to the PostgreSQL database
    private static final String URL = "jdbc:postgresql://localhost:5432/hotel_reservation_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "adminpassword";

    private static Connection connection = null;
    private static DatabaseConnection instance = null;

    private DatabaseConnection() {
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                // Load PostgreSQL JDBC driver
                Class.forName("org.postgresql.Driver");

                // Create a new connection to the database
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection established successfully!");
            } catch (ClassNotFoundException e) {
                System.err.println("PostgreSQL JDBC Driver not found. Include it in your library path.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Failed to establish a connection to the database.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Method to close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Database connection closed successfully.");
            } catch (SQLException e) {
                System.err.println("Failed to close the database connection.");
                e.printStackTrace();
            }
        }
    }
}

