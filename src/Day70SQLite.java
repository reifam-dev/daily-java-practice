import java.util.ArrayList;
import java.util.HashMap;

public class Day70SQLite {

    public static void main(String[] args) {

        InMemoryDB db = new InMemoryDB();

        db.insert("Alice",   "Engineering", 75000);
        db.insert("Bob",     "Marketing",   55000);
        db.insert("Charlie", "Engineering", 85000);
        db.insert("Diana",   "HR",          50000);
        db.insert("Eve",     "Marketing",   62000);

        System.out.println("=== All employees ===\n");
        for (String row : db.getAll()) {
            System.out.println("  " + row);
        }

        System.out.println("\n=== Engineering department ===\n");
        for (String row : db.getByDepartment("Engineering")) {
            System.out.println("  " + row);
        }

        System.out.println("\n=== Department summary ===\n");
        for (String summary : db.getDepartmentSummary()) {
            System.out.println("  " + summary);
        }

        System.out.println("\n=== Update salary (ID 1) ===\n");
        db.updateSalary(1, 80000);
        System.out.println("  " + db.getById(1));

        System.out.println("\n=== Delete (ID 4) ===\n");
        db.delete(4);
        System.out.println("  Remaining: " + db.getAll().size() + " employees");

    }

}

class EmployeeRecord {
    int id;
    String name;
    String department;
    double salary;

    public EmployeeRecord(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Employee(id=%d, name='%s', dept='%s', salary=£%.0f)",
                id, name, department, salary);
    }
}

class InMemoryDB {

    private ArrayList<EmployeeRecord> rows = new ArrayList<>();
    private int nextId = 1;

    public void insert(String name, String department, double salary) {
        rows.add(new EmployeeRecord(nextId++, name, department, salary));
    }

    public ArrayList<String> getAll() {
        ArrayList<String> result = new ArrayList<>();
        for (EmployeeRecord r : rows) result.add(r.toString());
        return result;
    }

    public String getById(int id) {
        for (EmployeeRecord r : rows) {
            if (r.id == id) return r.toString();
        }
        return "Not found.";
    }

    public ArrayList<String> getByDepartment(String dept) {
        ArrayList<String> result = new ArrayList<>();
        for (EmployeeRecord r : rows) {
            if (r.department.equals(dept)) result.add(r.toString());
        }
        return result;
    }

    public boolean updateSalary(int id, double newSalary) {
        for (EmployeeRecord r : rows) {
            if (r.id == id) { r.salary = newSalary; return true; }
        }
        return false;
    }

    public boolean delete(int id) {
        return rows.removeIf(r -> r.id == id);
    }

    public ArrayList<String> getDepartmentSummary() {
        HashMap<String, double[]> summary = new HashMap<>();
        for (EmployeeRecord r : rows) {
            summary.putIfAbsent(r.department, new double[]{0, 0});
            summary.get(r.department)[0]++;
            summary.get(r.department)[1] += r.salary;
        }
        ArrayList<String> result = new ArrayList<>();
        for (String dept : summary.keySet()) {
            double count = summary.get(dept)[0];
            double total = summary.get(dept)[1];
            result.add(String.format("%-15s count=%.0f  avg=£%.0f  total=£%.0f",
                    dept, count, total / count, total));
        }
        return result;
    }

}