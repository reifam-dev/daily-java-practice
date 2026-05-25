// Day 45 - Error Finding Quiz
// Find and fix the bugs

public class Day45ErrorQuiz {

    private String owner;
    private double balance;

    public Day45ErrorQuiz(String owner, double balance) {
        owner = owner;              // Bug 1 - missing this
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance =- amount;          // Bug 2 - wrong operator
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return owner + ": £" + balance;   // Bug 3 - owner null due to Bug 1
    }

    public static void main(String[] args) {
        Day45ErrorQuiz acc = new Day45ErrorQuiz("Alice", 100.0);
        acc.deposit(50.0);
        acc.withdraw(30.0);
        System.out.println(acc);
    }

}