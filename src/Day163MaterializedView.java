import java.util.HashMap;
import java.util.Map;

final class Deal {
    final String region;
    final double marketValue;

    Deal(String region, double marketValue) {
        this.region = region;
        this.marketValue = marketValue;
    }
}

/**
 * A precomputed, denormalised summary refreshed on demand, so an
 * expensive aggregation only runs once per refresh rather than on
 * every read.
 */
public class Day163MaterializedView {
    private static final Map<String, Deal> deals = new HashMap<>();
    private static Map<String, Double> regionTotalsView = new HashMap<>();

    static {
        deals.put("deal-1", new Deal("London", 12500000.0));
        deals.put("deal-2", new Deal("Midlands", 34200000.0));
        deals.put("deal-3", new Deal("London", 8100000.0));
    }

    private static void refreshRegionTotals() {
        Map<String, Double> totals = new HashMap<>();
        for (Deal deal : deals.values()) {
            totals.merge(deal.region, deal.marketValue, Double::sum);
        }
        regionTotalsView = totals;
    }

    private static double getRegionTotal(String region) {
        return regionTotalsView.getOrDefault(region, 0.0);
    }

    public static void main(String[] args) {
        refreshRegionTotals();
        System.out.println(getRegionTotal("London"));
        System.out.println(getRegionTotal("Midlands"));
    }
}