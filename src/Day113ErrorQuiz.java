import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Day113ErrorQuiz {

    private static void runPipeline(String dbUrl) throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl);
        Statement statement = connection.createStatement();
        statement.execute(
                "CREATE TABLE IF NOT EXISTS deals (name TEXT, ltv_pct REAL)");

        statement.execute(
                "INSERT INTO deals VALUES ('Riverside JV', 60.0)");
        statement.execute(
                "INSERT INTO deals VALUES ('Westgate Retail', 65.0)")

        PreparedStatement query = connection.prepareStatement(
                "SELECT name, ltv_pct FROM deals WHERE ltv_pct > ?");
        query.setDouble(1, 55.0);

        ResultSet results = query.executeQuery();
        while (results.next()) {
            System.out.println(results.getString("name") + " " + results.getDouble("ltv_pct"));
        }
    }

    public static void main(String[] args) throws SQLException {
        runPipeline("jdbc:sqlite:deals.db");
    }
}