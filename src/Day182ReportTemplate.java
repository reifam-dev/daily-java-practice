import java.util.List;

/**
 * Template Method: the abstract base class owns the ORDER of the
 * algorithm in a final generate(). Subclasses customise the individual
 * steps (hooks) but cannot replace or reorder the algorithm itself.
 */
public class Day182ReportTemplate {

    private abstract static class ReportGenerator {
        String header() {
            return "=== DEAL REPORT ===";
        }

        abstract String body();

        String footer() {
            return "=== END ===";
        }

        final String generate() {
            return header() + "\n" + body() + "\n" + footer();
        }
    }

    private static final class DealReport extends ReportGenerator {
        private final List<String> deals;

        DealReport(List<String> deals) {
            this.deals = deals;
        }

        @Override
        String body() {
            return String.join("\n", deals);
        }
    }

    private static final class QuickReport extends ReportGenerator {
        private final List<String> deals;

        QuickReport(List<String> deals) {
            this.deals = deals;
        }

        @Override
        String header() {
            return "--- QUICK ---";
        }

        @Override
        String body() {
            return String.join(", ", deals);
        }
    }

    public static void main(String[] args) {
        List<String> deals = List.of("Riverside JV", "Westgate Retail");
        System.out.println(new DealReport(deals).generate());
        System.out.println(new QuickReport(deals).generate());
    }
}