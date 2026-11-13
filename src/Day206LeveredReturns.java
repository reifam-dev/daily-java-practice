import java.util.Arrays;

/**
 * Borrowing changes the equity cash flows in three places: the loan
 * REDUCES the day-0 equity cheque, every year pays debt service (interest
 * AND principal), and the outstanding balance is repaid out of the sale
 * proceeds. Leverage helps only when the asset's unlevered return EXCEEDS
 * the cost of debt - otherwise it is negative leverage.
 */
public class Day206LeveredReturns {

    private static double npv(double rate, double[] flows) {
        double total = 0.0;
        for (int t = 0; t < flows.length; t++) {
            total += flows[t] / Math.pow(1 + rate, t);
        }
        return total;
    }

    private static double irr(double[] flows) {
        double low = -0.99;
        double high = 1.0;
        if (npv(low, flows) * npv(high, flows) > 0) {
            throw new IllegalArgumentException("IRR is not bracketed");
        }
        for (int i = 0; i < 200; i++) {
            double mid = (low + high) / 2;
            double value = npv(mid, flows);
            if (Math.abs(value) < 1e-9) {
                return mid;
            }
            if (value > 0) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return (low + high) / 2;
    }

    private static double monthlyPayment(double principal, double annualRate, int years) {
        int n = years * 12;
        if (annualRate == 0.0) {
            return principal / n;
        }
        double monthlyRate = annualRate / 12;
        return principal * monthlyRate / (1 - Math.pow(1 + monthlyRate, -n));
    }

    private static double loanBalance(double principal, double annualRate, int years, int monthsPaid) {
        if (annualRate == 0.0) {
            return principal * (1 - (double) monthsPaid / (years * 12));
        }
        double monthlyRate = annualRate / 12;
        double growth = Math.pow(1 + monthlyRate, monthsPaid);
        double payment = monthlyPayment(principal, annualRate, years);
        return principal * growth - payment * (growth - 1) / monthlyRate;
    }

    private static double[] leveredCashFlows(double[] unlevered, double loan,
                                             double annualRate, int amortYears) {
        int holdYears = unlevered.length - 1;
        double annualService = monthlyPayment(loan, annualRate, amortYears) * 12;

        double[] flows = unlevered.clone();
        flows[0] += loan;
        for (int year = 1; year <= holdYears; year++) {
            flows[year] -= annualService;
        }
        flows[holdYears] -= loanBalance(loan, annualRate, amortYears, holdYears * 12);
        return flows;
    }

    private static String leverageEffect(double unleveredIrr, double debtRate) {
        if (unleveredIrr > debtRate) {
            return "positive";
        }
        if (unleveredIrr < debtRate) {
            return "negative";
        }
        return "neutral";
    }

    public static void main(String[] args) {
        double[] unlevered = {-8544000.0, 495000.0, 495000.0, 374000.0, 252000.0, 9091500.0};
        double[] flows = leveredCashFlows(unlevered, 4500000.0, 0.055, 25);
        System.out.println(Arrays.toString(flows));
        System.out.printf("unlevered %.2f%%, levered %.2f%%%n", irr(unlevered) * 100, irr(flows) * 100);
        System.out.println(leverageEffect(irr(unlevered), 0.055));
    }
}