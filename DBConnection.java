import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Local H2 DB create cheyadam project folder lo
                conn = DriverManager.getConnection("jdbc:h2:./librarydb", "sa", "");
                System.out.println("Database Connected!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
