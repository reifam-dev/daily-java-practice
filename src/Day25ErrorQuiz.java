// Day 25 - Error Finding Quiz
// Find and fix the bugs

public class Day25ErrorQuiz {

    private String itemName;
    private int quantity;

    public Day25ErrorQuiz(String itemName, int quantity) {
        itemName = itemName;       // Bug 1 - missing this
        this.quantity = quantity;
    }

    public void sell(int amount) {
        quantity =- amount;        // Bug 2 - wrong operator, should be -=
    }

    public int getQuantity() {
        return quantity;
    }

    public static void main(String[] args) {
        Day25ErrorQuiz item = new Day25ErrorQuiz("Apples", 20)
        item.sell(5);              // Bug 3 - missing semicolon above
        System.out.println(item.getQuantity());
    }

}