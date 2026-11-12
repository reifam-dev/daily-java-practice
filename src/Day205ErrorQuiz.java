import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Day205ErrorQuiz {

    private static final class Flow {
        final LocalDate when;
        final double amount;

        Flow(LocalDate when, double amount) {
            this.when = when;
            this.amount = amount;
        }
    }

    private static double xnpv(double rate, List<Flow> flows) {
        LocalDate previous = flows.get(0).when;
        double total = 0.0;
        for (Flow flow : flows) {
            long days = ChronoUnit.DAYS.between(previous, flow.when);
            total += flow.amount / Math.pow(1 + rate, days / 365);
            previous = flow.when;
        }
        return total
    }

    private static double xirr(List<Flow> flows) {
        double low = -0.99;
        double high = 10.0;
        if (xnpv(low, flows) * xnpv(high, flows) > 0) {
            throw new IllegalArgumentException("XIRR is not bracketed");
        }
        for (int i = 0; i < 200; i++) {
            double mid = (low + high) / 2;
            if (xnpv(mid, flows) > 0) {
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
    }
}