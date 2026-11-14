/**
 * All three are multiples of PAID-IN capital (money actually called, not
 * the commitment): DPI = distributions / paid-in (cash back so far),
 * RVPI = NAV / paid-in (value still held), TVPI = DPI + RVPI (total
 * value). A fund that has not called any capital has no multiple, so that
 * case is rejected, not divided by zero.
 */
public class Day207FundMetrics {

    private static double dpi(double distributions, double paidIn) {
        if (paidIn <= 0) {
            throw new IllegalArgumentException("no paid-in capital yet: multiples are undefined");
        }
        return distributions / paidIn;
    }

    private static double rvpi(double nav, double paidIn) {
        if (paidIn <= 0) {
            throw new IllegalArgumentException("no paid-in capital yet: multiples are undefined");
        }
        return nav / paidIn;
    }

    private static double tvpi(double distributions, double nav, double paidIn) {
        return dpi(distributions, paidIn) + rvpi(nav, paidIn);
    }

    public static void main(String[] args) {
        double paidIn = 4000000.0 + 3000000.0 + 1000000.0;
        double distributions = 500000.0 + 1500000.0;
        double nav = 7200000.0;
        System.out.printf("dpi %.4f%n", dpi(distributions, paidIn));
        System.out.printf("rvpi %.4f%n", rvpi(nav, paidIn));
        System.out.printf("tvpi %.4f%n", tvpi(distributions, nav, paidIn));
        try {
            dpi(0.0, 0.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}