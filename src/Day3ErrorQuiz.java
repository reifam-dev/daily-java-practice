// Day 3 - Error Finding Quiz (Fix polymorphism issues)

public class Day3ErrorQuiz {
    public static void main(String[] args) {
        CheckingAccount ca = new CheckingAccount(100);
        ca.withdraw(30);
        SavingsAccount sa = new SavingsAccount(200);
        sa.withdraw(50);
        System.out.println(ca.getBalance() + " " + sa.getBalance());
    }
}

class Account {
    protected double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}

class CheckingAccount extends Account {
    public CheckingAccount(double balance) {
        super(balance);
    }

    public void withdraw(double amount) {
        balance -= amount + 1;   // Fee
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(double balance) {
        super(balance);
    }

    public void withdraw(double amount) {
        System.out.println("Not allowed");   // Wrong
    }
}