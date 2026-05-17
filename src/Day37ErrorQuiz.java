// Day 37 - Error Finding Quiz
// Find and fix the bugs

public class Day37ErrorQuiz {

    private String name;
    private int quantity;

    public Day37ErrorQuiz(String name, int quantity) {
        name = name;             // Bug 1 - missing this
        this.quantity = quantity;
    }

    public boolean isLowStock() {
        return quantity < 5;
    }

    public void restock(int amount) {
        quantity =+ amount;      // Bug 2 - wrong operator
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return name + ": " + quantity;  // Bug 3 - name is null due to Bug 1
    }

    public static void main(String[] args) {
        Day37ErrorQuiz item = new Day37ErrorQuiz("Apple", 3);
        System.out.println(item);
        System.out.println("Low stock: " + item.isLowStock());
        item.restock(10);
        System.out.println("After restock: " + item.getQuantity());
    }

}