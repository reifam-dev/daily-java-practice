import java.util.HashMap;
import java.util.Map;

public class Day163ErrorQuiz {
    private static Map<String, Double> deals = new HashMap<>();
    private static Map<String, Double> regionTotalsView = new HashMap<>();

    static {
        deals.put("deal-1", 12500000.0);
    }

    private static void refreshRegionTotals() {
        Map<String, Double> totals = new HashMap<>();
        totals.put("London", 12500000.0);
        totals.put("London", 8100000.0)
        regionTotalsView = totals;
    }

    private static double getRegionTotal(String region) {
        return regionTotalsView.getOrDefault(region, 0.0);
    }

    public static void main(String[] args) {
        refreshRegionTotals();
        System.out.println(getRegionTotal("London"));
    }
}