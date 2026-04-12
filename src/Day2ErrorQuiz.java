// Day 2 - Error Finding Quiz (Fixed inheritance issues)

public class Day2ErrorQuiz {
    public static void main(String[] args) {
        SavingsAccount sa = new SavingsAccount(100, 0.05);
        sa.deposit(50);
        sa.addInterest();
        System.out.println("Final balance: " + sa.getBalance());
    }
}