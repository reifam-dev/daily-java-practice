public class Day179ErrorQuiz {

    private static double monthlyPayment(double principal, double annualRate, int years) {
        int n = years * 12;
        if (annualRate == 0.0) {
            return principal / n;
        }
        double monthlyRate = annualRate / 12;
        return principal * monthlyRate / (1 - Math.pow(1 + monthlyRate, -n));
    }

    public static void main(String[] args) {
        double principal = 250000.0;
        double annualRate = 0.05;
        int years = 25;
        int n = years * 12;
        double payment = monthlyPayment(principal, annualRate, years);

        double balance = principal;
        int rows = 0;
        double lastInterest = 0.0;
        for (int month = 1; month < n; month++) {
            double interest = balance * annualRate;
            double principalPaid = payment - interest
            balance -= principalPaid;
            lastInterest = interest;
            rows++;
        }
        System.out.printf("rows=%d lastInterest=%.2f closingBalance=%.2f%n", rows, lastInterest, balance);
    }
}