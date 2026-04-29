// Day 19 - Error Finding Quiz
// Find and fix the bugs

public class Day19ErrorQuiz {

    String name;
    double price;

    public Day19ErrorQuiz(String name, double price) {
        name = name;       // Bug 1
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static void main(String[] args) {
        Day19ErrorQuiz fruit = new Day19ErrorQuiz("Apple", 0.50)
        System.out.println(fruit.getName());    // Bug 2
        System.out.println(fruit.getPrice());
    }

}