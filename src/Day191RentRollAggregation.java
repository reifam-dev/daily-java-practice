import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Group-by with accumulation: rent is ADDED to its sector's running
 * total via Map.merge, never assigned over it. Shares are each
 * sector's rent over the GRAND total, and the result is ordered
 * largest sector first.
 */
public class Day191RentRollAggregation {

    private static Map<String, Double> rentBySector(String[] sectors, double[] rents) {
        Map<String, Double> totals = new LinkedHashMap<>();
        for (int i = 0; i < sectors.length; i++) {
            totals.merge(sectors[i], rents[i], Double::sum);
        }

        List<Map.Entry<String, Double>> entries = new ArrayList<>(totals.entrySet());
        entries.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        Map<String, Double> sorted = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : entries) {
            sorted.put(entry.getKey(), entry.getValue());
        }
        return sorted;
    }

    private static Map<String, Double> shareOfTotal(Map<String, Double> totals) {
        double grandTotal = 0.0;
        for (double rent : totals.values()) {
            grandTotal += rent;
        }
        if (grandTotal <= 0) {
            throw new IllegalArgumentException("grand total must be positive");
        }
        Map<String, Double> shares = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : totals.entrySet()) {
            shares.put(entry.getKey(), Math.round(entry.getValue() / grandTotal * 1000.0) / 10.0);
        }
        return shares;
    }

    public static void main(String[] args) {
        String[] sectors = {"logistics", "retail", "office", "logistics", "retail"};
        double[] rents = {250000.0, 120000.0, 180000.0, 90000.0, 60000.0};
        Map<String, Double> totals = rentBySector(sectors, rents);
        System.out.println(totals);
        System.out.println(shareOfTotal(totals));
    }
}