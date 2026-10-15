import java.util.List;

public class Day182ErrorQuiz {

    private abstract static class ReportGenerator {
        String header() {
            return "=== DEAL REPORT ===";
        }

        abstract String body();

        String footer() {
            return "=== END ===";
        }

        String generate() {
            return footer() + "\n" + header() + "\n" + body();
        }
    }

    private static class DealReport extends ReportGenerator {
        private final List<String> deals;

        DealReport(List<String> deals) {
            this.deals = deals;
        }

        @Override
        String body() {
            return String.join("\n", deals);
        }
    }

    private static class QuickReport extends ReportGenerator {
        private final List<String> deals;

        QuickReport(List<String> deals) {
            this.deals = deals;
        }

        @Override
        String body() {
            return String.join(", ", deals);
        }

        @Override
        String generate() {
            return String.join(", ", deals)
        }
    }

    public static void main(String[] args) {
        List<String> deals = List.of("Riverside JV", "Westgate Retail");
        System.out.println(new DealReport(deals).generate());
        System.out.println(new QuickReport(deals).generate());
    }
}