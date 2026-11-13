public class Day206ErrorQuiz {

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
            if (npv(mid, flows) > 0) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return (low + high) / 2;
    }

    private static double monthlyPayment(double principal, double annualRate, int years) {
        double monthlyRate = annualRate / 12;
        int n = years * 12;
        return principal * monthlyRate / (1 - Math.pow(1 + monthlyRate, -n));
    }

    private static double[] leveredCashFlows(double[] unlevered, double loan,
                                             double annualRate, int amortYears) {
        int holdYears = unlevered.length - 1;
        double annualService = monthlyPayment(loan, annualRate, amortYears) * 12
        double[] flows = unlevered.clone();
        flows[0] += loan;
        for (int year = 1; year <= holdYears; year++) {
            flows[year] -= annualService;
        }
        return flows;
    }

    private static String leverageEffect(double unleveredIrr, double debtRate) {
        return unleveredIrr < debtRate ? "positive" : "negative";
    }

    public static void main(String[] args) {
        double[] unlevered = {-8544000.0, 495000.0, 495000.0, 374000.0, 252000.0, 9091500.0};
        double[] flows = leveredCashFlows(unlevered, 4500000.0, 0.055, 25);
        System.out.println(java.util.Arrays.toString(flows));
        System.out.printf("unlevered %.4f, levered %.4f%n", irr(unlevered), irr(flows));
        System.out.println(leverageEffect(irr(unlevered), 0.055));
    }
}