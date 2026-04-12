public class Day2SavingsAccount {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount(1000.0, 0.05);
        savings.deposit(200.0);
        savings.addInterest();
        System.out.printf("Final balance: $%.2f%n", savings.getBalance());
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) throw new IllegalArgumentException("Negative balance");
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}

class SavingsAccount extends BankAccount {
    private final double interestRate;

    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance);
        if (interestRate < 0) throw new IllegalArgumentException("Negative rate");
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
    }
}