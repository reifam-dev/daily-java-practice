// Day 33 - Error Finding Quiz
// Find and fix the bugs

public class Day33ErrorQuiz {

    private String name;
    private double price;
    private int quantity;

    public Day33ErrorQuiz(String name, double price, int quantity) {
        name = name;              // Bug 1 - missing this
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    public void restock(int amount) {
        quantity =+ amount;       // Bug 2 - wrong operator
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return name + " x" + quantity + " @ £" + price;
    }

    public static void main(String[] args) {
        Day33ErrorQuiz item = new Day33ErrorQuiz("Apple", 0.50, 10)
        item.restock(5);          // Bug 3 - missing semicolon above
        System.out.println(item);
        System.out.println(item.getQuantity());
    }

}
