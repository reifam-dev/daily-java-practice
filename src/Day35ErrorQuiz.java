// Day 35 - Error Finding Quiz
// Find and fix the bugs

public class Day35ErrorQuiz {

    private String bidder;
    private double amount;

    public Day35ErrorQuiz(String bidder, double amount) {
        bidder = bidder;           // Bug 1 - missing this
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getBidder() {
        return bidder;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Day35ErrorQuiz)) return false;
        Day35ErrorQuiz other = (Day35ErrorQuiz) obj;
        return amount == other.amount;  // Bug 2 - comparing doubles with ==
    }

    @Override
    public String toString() {
        return bidder + " bid £" + amount;  // Bug 3 - bidder is null due to Bug 1
    }

    public static void main(String[] args) {
        Day35ErrorQuiz b1 = new Day35ErrorQuiz("Alice", 100.0);
        Day35ErrorQuiz b2 = new Day35ErrorQuiz("Alice", 100.0);
        System.out.println(b1.equals(b2));
        System.out.println(b1);
    }

}