public class Day69Unittest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {

        System.out.println("=== BankAccount test suite ===\n");

        BankAccount acc;

        // test_initial_balance
        acc = new BankAccount("Alice", 100.0);
        assertEqual("initial balance", 100.0, acc.getBalance());
        assertEqual("owner", "Alice", acc.getOwner());

        // test_deposit
        acc = new BankAccount("Alice", 100.0);
        acc.deposit(50.0);
        assertEqual("deposit increases balance", 150.0, acc.getBalance());

        // test_withdraw
        acc = new BankAccount("Alice", 100.0);
        acc.withdraw(30.0);
        assertEqual("withdraw decreases balance", 70.0, acc.getBalance());

        // test_invalid_deposit
        acc = new BankAccount("Alice", 100.0);
        assertThrows("deposit zero raises", () -> acc.deposit(0));

        // test_insufficient_funds
        BankAccount acc2 = new BankAccount("Alice", 100.0);
        assertThrows("withdraw too much raises", () -> acc2.withdraw(200.0));

        System.out.printf("%n  Results: %d passed, %d failed%n", passed, failed);

    }

    static void assertEqual(String label, double expected, double actual) {
        if (Math.abs(expected - actual) < 0.0001) {
            System.out.println("  ✓ " + label);
            passed++;
        } else {
            System.out.println("  ✗ " + label
                    + " — expected " + expected + ", got " + actual);
            failed++;
        }
    }

    static void assertEqual(String label, String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("  ✓ " + label);
            passed++;
        } else {
            System.out.println("  ✗ " + label
                    + " — expected '" + expected + "', got '" + actual + "'");
            failed++;
        }
    }

    static void assertThrows(String label, Runnable action) {
        try {
            action.run();
            System.out.println("  ✗ " + label + " — no exception thrown");
            failed++;
        } catch (Exception e) {
            System.out.println("  ✓ " + label + " (" + e.getClass().getSimpleName() + ")");
            passed++;
        }
    }

}

class BankAccount {

    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive.");
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive.");
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds.");
        balance -= amount;
    }

    public double getBalance() { return balance; }
    public String getOwner() { return owner; }

}