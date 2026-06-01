import java.util.ArrayList;
import java.util.Collections;

public class Day52DataClass {

    public static void main(String[] args) {

        System.out.println("=== Student record ===\n");
        Student alice = new Student("Alice", 20);
        alice.addGrade(85.0);
        alice.addGrade(92.0);
        alice.addGrade(78.0);

        System.out.println("  " + alice);
        System.out.printf("  Average  : %.1f%n", alice.averageGrade());
        System.out.println("  Passing  : " + alice.isPassing());

        System.out.println("\n=== Product with sorting ===\n");
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99, "Electronics"));
        products.add(new Product("Apple", 0.50, "Fruit"));
        products.add(new Product("Book", 12.99, "Education"));

        Collections.sort(products);
        System.out.println("  Sorted by price:");
        for (Product p : products) {
            System.out.println("    " + p);
        }

        products.get(0).applyDiscount(10);
        System.out.println("\n  After 10% discount: " + products.get(0));

    }

}

class Student implements Comparable<Student> {

    private final String name;
    private final int age;
    private ArrayList<Double> grades;
    private static final double PASS_MARK = 50.0;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        }
    }

    public double averageGrade() {
        if (grades.isEmpty()) return 0.0;
        double total = 0;
        for (double g : grades) total += g;
        return total / grades.size();
    }

    public boolean isPassing() {
        return averageGrade() >= PASS_MARK;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("Student(name='%s', age=%d, grades=%s)",
                name, age, grades);
    }

}

class Product implements Comparable<Product> {

    private String name;
    private double price;
    private String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public void applyDiscount(double percent) {
        if (percent > 0 && percent < 100) {
            price -= price * (percent / 100);
        }
    }

    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return String.format("Product(name='%s', price=£%.2f, category='%s')",
                name, price, category);
    }

}