/**
 * NPV discounts each flow by its period index (the day-0 outlay is not
 * discounted). IRR is found by bisection: NPV falls as the rate rises,
 * so a positive NPV means the true IRR is higher. The rate bracket is
 * validated first so impossible inputs fail loudly.
 */
public class Day178NpvIrr {

    private static double npv(double rate, double[] cashFlows) {
        double total = 0.0;
        for (int t = 0; t < cashFlows.length; t++) {
            total += cashFlows[t] / Math.pow(1 + rate, t);
        }
        return total;
    }

    private static double irr(double[] cashFlows) {
        double low = -0.99;
        double high = 1.0;
        if (npv(low, cashFlows) * npv(high, cashFlows) > 0) {
            throw new IllegalArgumentException("IRR is not bracketed");
        }
        for (int i = 0; i < 200; i++) {
            double mid = (low + high) / 2;
            double value = npv(mid, cashFlows);
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

    public static void main(String[] args) {
        double[] flows = {-1000.0, 300.0, 400.0, 500.0};
        System.out.printf("%.2f%n", npv(0.10, flows));
        System.out.printf("%.4f%n", irr(flows));
        try {
            irr(new double[]{100.0, 100.0, 100.0});
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}