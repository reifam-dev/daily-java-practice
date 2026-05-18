import java.util.ArrayList;

public class Day38Decorator {

    public static void main(String[] args) {

        BankAccount alice = new BankAccount("Alice", 100.0);
        BankAccount bob = new BankAccount("Bob", 200.0);

        System.out.println("Initial balances:");
        System.out.println("  " + alice);
        System.out.println("  " + bob);

        alice.deposit(50.0);
        alice.deposit(-10.0);    // Should print warning

        bob.withdraw(75.0);
        bob.withdraw(200.0);     // Should print warning - insufficient funds

        System.out.println("\nFinal balances:");
        System.out.println("  " + alice);
        System.out.println("  " + bob);

        System.out.println("\nAlice transaction log:");
        for (String entry : alice.getLog()) {
            System.out.println("  " + entry);
        }

    }

}

class BankAccount {

    private String owner;
    private double balance;
    private ArrayList<String> log;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
        this.log = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        log.add(String.format("Deposited £%.2f — balance: £%.2f",
                amount, balance));
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.printf("Insufficient funds. Balance: £%.2f, Requested: £%.2f%n",
                    balance, amount);
            return;
        }
        balance -= amount;
        log.add(String.format("Withdrew £%.2f — balance: £%.2f",
                amount, balance));
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getLog() {
        return log;
    }

    @Override
    public String toString() {
        return String.format("BankAccount(owner='%s', balance=£%.2f)",
                owner, balance);
    }

}