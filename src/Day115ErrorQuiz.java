import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Day115ErrorQuiz {

    private static void setupTable(String dbUrl) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS deals (deal_name TEXT PRIMARY KEY, market_value REAL)");
        }
    }

    private static void upsertDeal(String dbUrl, String dealName, double marketValue) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbUrl)) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO deals (deal_name, market_value) VALUES (?, ?)");
            statement.setString(1, dealName);
            statement.setDouble(2, marketValue);
            statement.executeUpdate();
        }
    }

    public static void main(String[] args) throws SQLException {
        String db = "jdbc:sqlite:deals_incremental.db";
        setupTable(db);
        upsertDeal(db, "Riverside JV", 12500000.0);
        upsertDeal(db, "Riverside JV", 13100000.0);

        try (Connection connection = DriverManager.getConnection(db);
             Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM deals")) {
            while (results.next()) {
                System.out.println(results.getString("deal_name") + " " + results.getDouble("market_value"));
            }
        }
    }
}