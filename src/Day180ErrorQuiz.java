import java.util.LinkedHashMap;
import java.util.Map;

public class Day180ErrorQuiz {

    private static Map<String, Double> runWaterfall(
            double cash, double capital, double prefDue, double gpPromotePct) {
        if (cash < 0 || capital < 0 || prefDue < 0) {
            throw new IllegalArgumentException("amounts must be non-negative");
        }
        if (gpPromotePct < 0.0 || gpPromotePct > 1.0) {
            throw new IllegalArgumentException("gpPromotePct must be between 0 and 1");
        }

        double remaining = cash;

        double capitalReturn = Math.min(remaining, capital);

        double prefPaid = Math.min(remaining, prefDue);
        remaining -= prefPaid;

        double gpShare = cash * gpPromotePct;
        double lpShare = remaining - gpShare

        Map<String, Double> result = new LinkedHashMap<>();
        result.put("lpTotal", capitalReturn + prefPaid + lpShare);
        result.put("gpTotal", gpShare);
        return result;
    }

    public static void main(String[] args) {
        Map<String, Double> result = runWaterfall(15000000.0, 10000000.0, 2400000.0, 0.20);
        System.out.println(result);
        System.out.println(result.get("lpTotal") + result.get("gpTotal"));
    }
}