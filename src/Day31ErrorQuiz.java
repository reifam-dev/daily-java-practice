// Day 31 - Error Finding Quiz
// Find and fix the bugs

public class Day31ErrorQuiz {

    private String name;
    private double price;

    public Day31ErrorQuiz(String name, double price) {
        name = name;            // Bug 1 - missing this
        this.price = price;
    }

    public void applyDiscount(double percentage) {
        price =- price * (percentage / 100);  // Bug 2 - wrong operator
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return "Product: " + name + ", £" + price;
    }

    public static void main(String[] args) {
        Day31ErrorQuiz p = new Day31ErrorQuiz("Apple", 1.00)
        p.applyDiscount(10);          // Bug 3 - missing semicolon above
        System.out.println(p);
    }

}