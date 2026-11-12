import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * IRR for cash flows that fall on real dates. Each flow is discounted by
 * its fractional years from the EARLIEST date (days / 365.0 - integer
 * division would throw away the fraction), the flows are sorted first so
 * input order cannot change the answer, and an unbracketed problem fails
 * loudly.
 */
public class Day205Xirr {

    private static final class Flow {
        final LocalDate when;
        final double amount;

        Flow(LocalDate when, double amount) {
            this.when = when;
            this.amount = amount;
        }
    }

    private static double xnpv(double rate, List<Flow> flows) {
        List<Flow> ordered = new ArrayList<>(flows);
        ordered.sort(Comparator.comparing((Flow flow) -> flow.when));
        LocalDate start = ordered.get(0).when;

        double total = 0.0;
        for (Flow flow : ordered) {
            double years = ChronoUnit.DAYS.between(start, flow.when) / 365.0;
            total += flow.amount / Math.pow(1 + rate, years);
        }
        return total;
    }

    private static double xirr(List<Flow> flows) {
        if (flows.size() < 2) {
            throw new IllegalArgumentException("need at least two cash flows");
        }
        double low = -0.99;
        double high = 10.0;
        if (xnpv(low, flows) * xnpv(high, flows) > 0) {
            throw new IllegalArgumentException("XIRR is not bracketed");
        }
        for (int i = 0; i < 300; i++) {
            double mid = (low + high) / 2;
            double value = xnpv(mid, flows);
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
        List<Flow> flows = new ArrayList<>();
        flows.add(new Flow(LocalDate.of(2026, 1, 1), -1000000.0));
        flows.add(new Flow(LocalDate.of(2026, 7, 1), 50000.0));
        flows.add(new Flow(LocalDate.of(2027, 1, 1), 50000.0));
        flows.add(new Flow(LocalDate.of(2028, 6, 30), 1200000.0));

        System.out.printf("%.4f%n", xirr(flows));
        List<Flow> reversed = new ArrayList<>(flows);
        Collections.reverse(reversed);
        System.out.printf("%.4f%n", xirr(reversed));

        List<Flow> allPositive = List.of(
                new Flow(LocalDate.of(2026, 1, 1), 100.0), new Flow(LocalDate.of(2027, 1, 1), 100.0));
        try {
            xirr(allPositive);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}