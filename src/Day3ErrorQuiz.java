// Day 3 - Error Finding Quiz (Fix polymorphism issues)

public class Day3ErrorQuiz {
    public static void main(String[] args) {
        CheckingAccount ca = new CheckingAccount(100);
        SavingsAccount sa = new SavingsAccount(200);

        ca.withdraw(30);   // should charge fee
        sa.withdraw(50);   // should be blocked

        System.out.println(ca.getBalance() + " " + sa.getBalance());
    }
}

class CheckingAccount extends Account {
    public CheckingAccount(double balance) {
        super(balance);
    }

    public void withdraw(double amount) {
        balance -= amount + 1;   // Fee - missing super() call
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(double balance) {
        super(balance);
    }

    public void withdraw(double amount) {
        System.out.println("Not allowed");   // Wrong logic - should throw exception
    }
}

class Account {
    protected double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    // Missing getBalance() method - this is one of the bugs to find
}