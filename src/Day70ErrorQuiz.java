// Day 70 - Error Finding Quiz
// Find and fix the bugs

import java.util.ArrayList;
import java.util.HashMap;

public class Day70ErrorQuiz {

    private String tableName;
    private ArrayList<HashMap<String, Object>> rows = new ArrayList<>();

    public Day70ErrorQuiz(String tableName) {
        tableName = tableName;         // Bug 1 - missing this
    }

    public void insert(String name, String dept, double salary) {
        HashMap<String, Object> row = new HashMap<>();
        row.put("name", name);
        row.put("dept", dept);
        row.put("salary", salary =+ 0);  // Bug 2 - wrong operator, should just be salary
        rows.add(row);
    }

    public ArrayList<HashMap<String, Object>> getAll() {
        return rows;
    }

    public double getTotalSalary() {
        double total = 0;
        for (HashMap<String, Object> row : rows) {
            total =+ (double) row.get("salary");  // Bug 3 - wrong operator, should be +=
        }
        return total;
    }

    public static void main(String[] args) {
        Day70ErrorQuiz db = new Day70ErrorQuiz("employees");
        db.insert("Alice", "Engineering", 75000);
        db.insert("Bob", "Marketing", 55000);
        System.out.println(db.getAll());
        System.out.println(db.getTotalSalary());
    }

}