import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Day148SqlInjectionSafe {
    private static void setup(String dbUrl) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS deals (name TEXT, market_value REAL)");
            stmt.execute("INSERT INTO deals VALUES ('Riverside JV', 12500000.0)");
        }
    }

    private static void findDeal(String dbUrl, String name) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM deals WHERE name = ?")) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        setup("jdbc:sqlite:deals148.db");
        findDeal("jdbc:sqlite:deals148.db", "x' OR '1'='1");
    }
}