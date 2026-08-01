import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A minimal ETL pipeline in Java: creates a deals table, loads sample
 * records, and queries by a parameterised LTV threshold, closing all
 * JDBC resources via try-with-resources.
 */
public class Day113DealEtlPipeline {

    private static void runPipeline(String dbUrl) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbUrl)) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(
                        "CREATE TABLE IF NOT EXISTS deals (name TEXT, ltv_pct REAL)");
                statement.execute(
                        "INSERT INTO deals VALUES ('Riverside JV', 60.0)");
                statement.execute(
                        "INSERT INTO deals VALUES ('Westgate Retail', 65.0)");
            }

            try (PreparedStatement query = connection.prepareStatement(
                    "SELECT name, ltv_pct FROM deals WHERE ltv_pct > ?")) {
                query.setDouble(1, 55.0);

                try (ResultSet results = query.executeQuery()) {
                    while (results.next()) {
                        System.out.println(
                                results.getString("name") + " " + results.getDouble("ltv_pct"));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        runPipeline("jdbc:sqlite:deals.db");
    }
}