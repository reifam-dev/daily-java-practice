import java.util.LinkedHashMap;
import java.util.Map;

public class Day191ErrorQuiz {

    private static Map<String, Double> rentBySector(String[] sectors, double[] rents) {
        Map<String, Double> totals = new LinkedHashMap<>();
        for (int i = 0; i < sectors.length; i++) {
            totals.put(sectors[i], rents[i]);
        }
        return totals;
    }

    private static Map<String, Double> shareOfTotal(Map<String, Double> totals) {
        Map<String, Double> shares = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : totals.entrySet()) {
            shares.put(entry.getKey(), Math.round(entry.getValue() / totals.size() * 1000.0) / 10.0)
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