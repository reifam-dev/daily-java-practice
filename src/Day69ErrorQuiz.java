// Day 69 - Error Finding Quiz
// Find and fix the bugs

public class Day69ErrorQuiz {

    private String owner;
    private double balance;

    public Day69ErrorQuiz(String owner, double balance) {
        owner = owner;               // Bug 1 - missing this
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive.");
        }
        balance =+ amount;           // Bug 2 - wrong operator, should be +=
    }

    public double getBalance() { return balance; }
    public String getOwner() { return owner; }

    public static void main(String[] args) {
        Day69ErrorQuiz acc = new Day69ErrorQuiz("Alice", 100.0);
        acc.deposit(50.0);
        System.out.println(acc.getBalance());
        System.out.println(acc.getOwner())   // Bug 3 - missing semicolon
    }

}