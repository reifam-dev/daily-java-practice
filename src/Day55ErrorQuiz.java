// Day 55 - Error Finding Quiz
// Find and fix the bugs

public class Day55ErrorQuiz {

    private String item;
    private int quantity;

    public Day55ErrorQuiz(String item, int quantity) {
        item = item;              // Bug 1 - missing this
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return quantity => 1;     // Bug 2 - invalid operator, should be >=
    }

    public void sell(int amount) {
        if (amount > quantity) {
            System.out.println("Not enough stock.");
            return;
        }
        quantity =- amount;       // Bug 3 - wrong operator, should be -=
    }

    @Override
    public String toString() {
        return item + " x" + quantity;
    }

    public static void main(String[] args) {
        Day55ErrorQuiz stock = new Day55ErrorQuiz("Apple", 10);
        System.out.println(stock.isAvailable());
        stock.sell(3);
        System.out.println(stock);
    }

}