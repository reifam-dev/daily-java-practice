// Day 38 - Error Finding Quiz
// Find and fix the bugs

public class Day38ErrorQuiz {

    private String owner;
    private double balance;

    public Day38ErrorQuiz(String owner, double balance) {
        owner = owner;              // Bug 1 - missing this
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        balance =+ amount;          // Bug 2 - wrong operator
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return owner + ": £" + balance;   // Bug 3 - owner is null due to Bug 1
    }

    public static void main(String[] args) {
        Day38ErrorQuiz acc = new Day38ErrorQuiz("Alice", 100.0);
        acc.deposit(50.0);
        System.out.println(acc);
        System.out.println(acc.getBalance());
    }

}