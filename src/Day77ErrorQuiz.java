// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;

public class Day77ErrorQuiz {

    private String dbName;
    private ArrayList<String[]> records;

    public Day77ErrorQuiz(String dbName) {
        dbName = dbName;                        // Bug 1: missing this
        this.records = new ArrayList<>();
    }

    public void insert(String sector, double value) {
        this.records.add(new String[]{sector, String.valueOf(value)});
    }

    public double totalValue() {
        double total = 0.0;
        for (String[] row : this.records) {
            total =* Double.parseDouble(row[1]);  // Bug 2: =* should be +=
        }
        return total;
    }

    public void printAll() {
        for (String[] row : this.records) {
            System.out.println(row[0] + " | " + row[1])
        }                                       // Bug 3: missing semicolon
    }

    @Override
    public String toString() {
        return "DB: " + dbName + " | Records: " + records.size();
    }

    public static void main(String[] args) {
        Day77ErrorQuiz db = new Day77ErrorQuiz("property_db");
        db.insert("Office", 80.0);
        db.insert("Retail", 30.0);
        System.out.println(db);
        db.printAll();
        System.out.println("Total: " + db.totalValue());
    }
}