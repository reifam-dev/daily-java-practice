import java.util.ArrayList;
import java.util.Collections;

public class Day53NamedTuple {

    public static void main(String[] args) {

        System.out.println("=== Immutable Point record ===\n");
        ImmutablePoint p1 = new ImmutablePoint(3, 4);
        ImmutablePoint p2 = new ImmutablePoint(3, 4);
        ImmutablePoint p3 = new ImmutablePoint(6, 8);

        System.out.println("  p1           : " + p1);
        System.out.println("  p1 == p2     : " + (p1 == p2));
        System.out.println("  p1.equals(p2): " + p1.equals(p2));
        System.out.println("  p1.equals(p3): " + p1.equals(p3));
        System.out.printf("  Distance     : %.2f%n%n",
                p1.distanceFromOrigin());

        System.out.println("=== Employee record ===\n");
        ImmutableEmployee emp = new ImmutableEmployee(
                "Alice", "Engineering", 45000.0);
        System.out.println("  " + emp);
        System.out.printf("  Bonus (10%%)  : £%.2f%n",
                emp.getBonus(10));
        System.out.println("  High earner  : " + emp.isHighEarner());

        ImmutableEmployee promoted = emp.withRaise(5000.0);
        System.out.println("  After raise  : " + promoted);
        System.out.println("  Original     : " + emp + "\n");

        System.out.println("=== Sorting employees ===\n");
        ArrayList<ImmutableEmployee> employees = new ArrayList<>();
        employees.add(new ImmutableEmployee("Charlie", "HR", 32000.0));
        employees.add(new ImmutableEmployee("Alice", "Engineering", 45000.0));
        employees.add(new ImmutableEmployee("Bob", "Marketing", 38000.0));

        Collections.sort(employees);
        for (ImmutableEmployee e : employees) {
            System.out.println("  " + e);
        }

    }

}

final class ImmutablePoint {

    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ImmutablePoint)) return false;
        ImmutablePoint other = (ImmutablePoint) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return String.format("Point(%d, %d)", x, y);
    }

}

final class ImmutableEmployee implements Comparable<ImmutableEmployee> {

    private final String name;
    private final String department;
    private final double salary;
    private static final double HIGH_EARNER_THRESHOLD = 50000.0;

    public ImmutableEmployee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public double getBonus(double percent) {
        return salary * percent / 100;
    }

    public boolean isHighEarner() {
        return salary >= HIGH_EARNER_THRESHOLD;
    }

    public ImmutableEmployee withRaise(double amount) {
        return new ImmutableEmployee(name, department, salary + amount);
    }

    @Override
    public int compareTo(ImmutableEmployee other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("Employee(name='%s', dept='%s', salary=£%.2f)",
                name, department, salary);
    }

}