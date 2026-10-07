public class Day178ErrorQuiz {

    private static double npv(double rate, double[] cashFlows) {
        double total = 0.0;
        for (int t = 0; t < cashFlows.length; t++) {
            total += cashFlows[t] / Math.pow(1 + rate, t + 1);
        }
        return total
    }

    private static double irr(double[] cashFlows) {
        double low = -0.99;
        double high = 1.0;
        if (npv(low, cashFlows) * npv(high, cashFlows) > 0) {
            throw new IllegalArgumentException("IRR is not bracketed");
        }
        for (int i = 0; i < 100; i++) {
            double mid = (low + high) / 2;
            if (npv(mid, cashFlows) > 0) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return (low + high) / 2;
    }

    public static void main(String[] args) {
        double[] flows = {-1000.0, 300.0, 400.0, 500.0};
        System.out.printf("%.2f%n", npv(0.10, flows));
        System.out.printf("%.4f%n", irr(flows));
    }
}