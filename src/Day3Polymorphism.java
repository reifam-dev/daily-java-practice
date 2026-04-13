public class Day3Polymorphism {
    public static void main(String[] args) {
        CheckingAccount checking = new CheckingAccount(100.0);
        SavingsAccount savings = new SavingsAccount(200.0);

        checking.deposit(50.0);
        checking.withdraw(30.0);   // Uses overridden method with fee

        System.out.printf("Checking balance: $%.2f%n", checking.getBalance());
        System.out.printf("Savings balance: $%.2f%n", savings.getBalance());
    }
}

class Account {
    private double balance;

    public Account(double initialBalance) {
        if (initialBalance < 0) throw new IllegalArgumentException("Negative balance");
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid amount");
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds");
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

class CheckingAccount extends Account {
    private static final double FEE = 1.0;

    public CheckingAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount + FEE);
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        throw new IllegalArgumentException("Withdrawals not allowed from SavingsAccount");
    }
}