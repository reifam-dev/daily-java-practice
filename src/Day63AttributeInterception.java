import java.util.ArrayList;

public class Day63AttributeInterception {

    public static void main(String[] args) {

        System.out.println("=== LoggedRecord ===\n");
        LoggedRecord rec = new LoggedRecord("Alice", 30, 75000.0);
        System.out.println("  " + rec);
        rec.setName("Alicia");
        rec.setSalary(80000.0);

        System.out.println("  Change log:");
        for (String entry : rec.getLog()) {
            System.out.println("    " + entry);
        }

        System.out.println("\n=== FrozenRecord ===\n");
        FrozenRecord point = new FrozenRecord("Alice", 30);
        System.out.println("  " + point);
        point.freeze();
        point.setName("Bob");

    }

}

class LoggedRecord {

    private String name;
    private int age;
    private double salary;
    private ArrayList<String> log = new ArrayList<>();

    public LoggedRecord(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }

    public void setName(String name) {
        log.add("SET name: '" + this.name + "' → '" + name + "'");
        this.name = name;
    }

    public void setSalary(double salary) {
        log.add("SET salary: " + this.salary + " → " + salary);
        this.salary = salary;
    }

    public ArrayList<String> getLog() { return log; }

    @Override
    public String toString() {
        return String.format("LoggedRecord(name='%s', age=%d, salary=%.0f)",
                name, age, salary);
    }

}

class FrozenRecord {

    private String name;
    private int age;
    private boolean frozen;

    public FrozenRecord(String name, int age) {
        this.name = name;
        this.age = age;
        this.frozen = false;
    }

    public void freeze() {
        frozen = true;
        System.out.println("  Record frozen.");
    }

    public void setName(String name) {
        if (frozen) {
            System.out.println("  Cannot set name — record is frozen.");
            return;
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("FrozenRecord(name='%s', age=%d, frozen=%b)",
                name, age, frozen);
    }

}