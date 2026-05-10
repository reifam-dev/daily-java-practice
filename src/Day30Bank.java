public class Day30Bank {

    public static void main(String[] args) {

        BankAccount alice = new BankAccount(1, "Alice", 1000);
        BankAccount bob = new BankAccount(2, "Bob", 500);

        System.out.println(alice.getOwner()
                + " balance: £" + alice.getBalance());
        System.out.println(bob.getOwner()
                + " balance: £" + bob.getBalance());

        alice.deposit(500);
        System.out.printf("After deposit - Alice: £%.2f%n",
                alice.getBalance());

        alice.withdraw(300);
        System.out.printf("After withdrawal - Alice: £%.2f%n",
                alice.getBalance());

        alice.withdraw(2000);   // Should print warning - insufficient funds

    }

}

class BankAccount {

    private int accountId;
    private String owner;
    private double balance;

    public BankAccount(int accountId, String owner, double balance) {
        this.accountId = accountId;
        this.owner = owner;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
        } else {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.printf("Insufficient funds. Balance: £%.2f, Requested: £%.2f%n",
                    balance, amount);
        } else {
            balance -= amount;
        }
    }