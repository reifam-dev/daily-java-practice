/**
 * Interest each month is charged on the outstanding balance at the
 * MONTHLY rate, the schedule covers every payment, a zero-rate loan is
 * straight-line, and the closing balance is clamped so floating-point
 * noise never shows as -0.00.
 */
public class Day179AmortisationSchedule {

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
        double monthlyRate = annualRate / 12;
        double payment = monthlyPayment(principal, annualRate, years);

        double balance = principal;
        for (int month = 1; month <= n; month++) {
            double interest = balance * monthlyRate;
            double principalPaid = payment - interest;
            balance = Math.max(balance - principalPaid, 0.0);
            if (month == 1 || month == n) {
                System.out.printf("%d %.2f %.2f %.2f%n", month, interest, principalPaid, balance);
            }
        }
    }
}