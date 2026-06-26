import java.util.ArrayList;

/**
 * Day 77 – PostgreSQL CRUD concepts in Java: in-memory simulation.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day77PropertyDatabase {

    private String dbName;
    private ArrayList<String[]> records;
    private int nextId;

    public Day77PropertyDatabase(String dbName) {
        this.dbName = dbName;
        this.records = new ArrayList<>();
        this.nextId = 1;
    }

    public String getDbName() { return this.dbName; }
    public int getRecordCount() { return this.records.size(); }

    public void insert(String sector, String region, double value, double yieldPct) {
        this.records.add(new String[]{
                String.valueOf(this.nextId++),
                sector, region,
                String.valueOf(value),
                String.valueOf(yieldPct)
        });
    }

    public ArrayList<String[]> fetchAll() {
        return new ArrayList<>(this.records);
    }

    public ArrayList<String[]> fetchBySector(String sector) {
        ArrayList<String[]> result = new ArrayList<>();
        for (String[] row : this.records) {
            if (row[1].equals(sector)) result.add(row);
        }
        return result;
    }

    public void updateValue(int id, double newValue) {
        for (String[] row : this.records) {
            if (Integer.parseInt(row[0]) == id) {
                row[3] = String.valueOf(newValue);
                return;
            }
        }
    }

    public void delete(int id) {
        this.records.removeIf(row -> Integer.parseInt(row[0]) == id);
    }

    public double meanYield() {
        if (this.records.isEmpty()) return 0.0;
        double total = 0.0;
        for (String[] row : this.records) total += Double.parseDouble(row[4]);
        return total / this.records.size();
    }

    public void printAll() {
        System.out.printf("%-4s %-12s %-14s %-8s %-6s%n", "ID", "Sector", "Region", "Value", "Yield");
        System.out.println("-".repeat(50));
        for (String[] row : this.records) {
            System.out.printf("%-4s %-12s %-14s %-8s %-6s%n",
                    row[0], row[1], row[2], row[3], row[4]);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "PropertyDatabase | db=%s | records=%d | meanYield=%.2f%%",
                this.dbName, this.records.size(), meanYield()
        );
    }

    public static void main(String[] args) {
        Day77PropertyDatabase db = new Day77PropertyDatabase("property_db");
        db.insert("Office", "London", 80.0, 4.5);
        db.insert("Retail", "Manchester", 30.0, 5.5);
        db.insert("Industrial", "Birmingham", 60.0, 4.8);
        db.printAll();
        db.updateValue(1, 85.0);
        db.delete(2);
        System.out.println("\nAfter update and delete:");
        db.printAll();
        System.out.println("\n" + db);
    }
}