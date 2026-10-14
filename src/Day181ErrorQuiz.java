import java.util.ArrayList;
import java.util.List;

public class Day181ErrorQuiz {

    private static final class Deal {
        final String name;
        final double yieldRate;
        final double ltv;
        final String sector;

        Deal(String name, double yieldRate, double ltv, String sector) {
            this.name = name;
            this.yieldRate = yieldRate;
            this.ltv = ltv;
            this.sector = sector;
        }
    }

    private interface Spec {
        boolean isSatisfiedBy(Deal deal);

        default Spec and(Spec other) {
            return deal -> isSatisfiedBy(deal) || other.isSatisfiedBy(deal);
        }

        default Spec not() {
            return deal -> isSatisfiedBy(deal);
        }
    }

    private static Spec minYield(double min) {
        return deal -> deal.yieldRate >= min;
    }

    private static Spec maxLtv(double max) {
        return deal -> deal.ltv <= max;
    }

    private static Spec sector(String sector) {
        return deal -> deal.sector.equals(sector)
    }

    public static void main(String[] args) {
        List<Deal> deals = List.of(
                new Deal("Riverside JV", 0.062, 0.60, "logistics"),
                new Deal("Westgate Retail", 0.071, 0.70, "retail"),
                new Deal("Docklands", 0.055, 0.55, "logistics"));

        Spec screen = minYield(0.06).and(maxLtv(0.65)).and(sector("retail").not());
        List<String> names = new ArrayList<>();
        for (Deal deal : deals) {
            if (screen.isSatisfiedBy(deal)) {
                names.add(deal.name);
            }
        }
        System.out.println(names);
    }
}