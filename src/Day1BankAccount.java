public class Day1BankAccount {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount(100);
        acc.deposit(50);
        acc.withdraw(30);
        System.out.println("Final balance: " + acc.getBalance());
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initial) {
        if (initial < 0) throw new IllegalArgumentException("Negative balance");
        this.balance = initial;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}