import java.util.ArrayList;
import java.util.List;

/**
 * DSCR = NOI / (interest + principal), ICR = NOI / interest, and
 * LTV = loan / value. A covenant is breached only when the ratio is
 * strictly on the wrong side of its limit - sitting exactly AT the
 * limit is compliant.
 */
public class Day189DebtCovenants {

    private static double dscr(double noi, double interest, double principal) {
        double debtService = interest + principal;
        if (debtService <= 0) {
            throw new IllegalArgumentException("debt service must be positive");
        }
        return noi / debtService;
    }

    private static double icr(double noi, double interest) {
        if (interest <= 0) {
            throw new IllegalArgumentException("interest must be positive");
        }
        return noi / interest;
    }

    private static double ltv(double loan, double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("value must be positive");
        }
        return loan / value;
    }

    private static boolean isBelowMinimum(double actual, double minimum) {
        return actual < minimum;
    }

    private static boolean isAboveMaximum(double actual, double maximum) {
        return actual > maximum;
    }

    private static List<String> checkCovenants(double noi, double interest, double principal,
                                               double loan, double value) {
        double minDscr = 1.25;
        double minIcr = 2.0;
        double maxLtv = 0.70;

        List<String> breaches = new ArrayList<>();
        double actualDscr = dscr(noi, interest, principal);
        double actualIcr = icr(noi, interest);
        double actualLtv = ltv(loan, value);

        if (isBelowMinimum(actualDscr, minDscr)) {
            breaches.add(String.format("DSCR %.2f below minimum %.2f", actualDscr, minDscr));
        }
        if (isBelowMinimum(actualIcr, minIcr)) {
            breaches.add(String.format("ICR %.2f below minimum %.2f", actualIcr, minIcr));
        }
        if (isAboveMaximum(actualLtv, maxLtv)) {
            breaches.add(String.format("LTV %.1f%% above maximum %.1f%%", actualLtv * 100, maxLtv * 100));
        }
        return breaches;
    }

    public static void main(String[] args) {
        System.out.printf("%.4f%n", dscr(1300000.0, 600000.0, 440000.0));
        System.out.printf("%.4f%n", ltv(12000000.0, 18000000.0));
        System.out.println(isBelowMinimum(1.25, 1.25));
        System.out.println(checkCovenants(1300000.0, 600000.0, 440000.0, 12000000.0, 18000000.0));
        System.out.println(checkCovenants(1200000.0, 600000.0, 440000.0, 12000000.0, 16000000.0));
    }
}