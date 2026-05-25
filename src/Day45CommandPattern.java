import java.util.ArrayList;

public class Day45CommandPattern {

    public static void main(String[] args) {

        BankAccount acc = new BankAccount("Alice", 100.0);
        CommandProcessor processor = new CommandProcessor();

        System.out.println("Initial: " + acc + "\n");

        System.out.println("=== Executing commands ===\n");
        processor.execute(new DepositCommand(acc, 50.0));
        processor.execute(new DepositCommand(acc, 25.0));
        processor.execute(new WithdrawCommand(acc, 30.0));

        System.out.println("\nBalance: " + acc);
        System.out.println("History: " + processor.getHistoryCount() + " commands\n");

        System.out.println("=== Undoing last command ===\n");
        processor.undoLast();
        System.out.println("\nAfter undo: " + acc + "\n");

        System.out.println("=== Undoing all ===\n");
        processor.undoLast();
        processor.undoLast();
        processor.undoLast();   // Nothing to undo

        System.out.println("\nFinal: " + acc);

    }

}

interface Command {
    void execute();
    void undo();
}

class BankAccount {

    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.printf("  Deposited £%.2f — balance: £%.2f%n", amount, balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.printf("  Insufficient funds. Balance: £%.2f%n", balance);
            return;
        }
        balance -= amount;
        System.out.printf("  Withdrew £%.2f — balance: £%.2f%n", amount, balance);
    }

    public double getBalance() { return balance; }

    @Override
    public String toString() {
        return String.format("BankAccount('%s', £%.2f)", owner, balance);
    }

}

class DepositCommand implements Command {
    private BankAccount account;
    private double amount;
    public DepositCommand(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }
    public void execute() { account.deposit(amount); }
    public void undo()    { account.withdraw(amount); }
}

class WithdrawCommand implements Command {
    private BankAccount account;
    private double amount;
    public WithdrawCommand(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }
    public void execute() { account.withdraw(amount); }
    public void undo()    { account.deposit(amount); }
}

class CommandProcessor {

    private ArrayList<Command> history = new ArrayList<>();

    public void execute(Command command) {
        command.execute();
        history.add(command);
    }

    public void undoLast() {
        if (history.isEmpty()) {
            System.out.println("  Nothing to undo.");
            return;
        }
        Command command = history.remove(history.size() - 1);
        command.undo();
    }

    public int getHistoryCount() {
        return history.size();
    }

}