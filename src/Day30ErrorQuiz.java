// Day 30 - Error Finding Quiz
// Find and fix the bugs

public class Day30ErrorQuiz {

    private String owner;
    private double balance;

    public Day30ErrorQuiz(String owner, double balance) {
        owner = owner;              // Bug 1 - missing this
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance =+ amount;          // Bug 2 - wrong operator, should be +=
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    public static void main(String[] args) {
        Day30ErrorQuiz account = new Day30ErrorQuiz("Alice", 500)
        account.deposit(200);       // Bug 3 - missing semicolon above
        System.out.println(account.getOwner() + ": £" + account.getBalance());
    }

}