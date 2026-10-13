import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Cash flows down a fixed order of tiers, each paid from what is LEFT
 * after the tiers above it: return of capital, then preferred return,
 * then the residual split between LP and GP by the promote percentage.
 */
public class Day180DistributionWaterfall {

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
        remaining -= capitalReturn;

        double prefPaid = Math.min(remaining, prefDue);
        remaining -= prefPaid;

        double gpShare = remaining * gpPromotePct;
        double lpShare = remaining - gpShare;

        Map<String, Double> result = new LinkedHashMap<>();
        result.put("returnOfCapital", capitalReturn);
        result.put("preferredReturn", prefPaid);
        result.put("lpResidual", lpShare);
        result.put("gpPromote", gpShare);
        result.put("lpTotal", capitalReturn + prefPaid + lpShare);
        result.put("gpTotal", gpShare);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(runWaterfall(15000000.0, 10000000.0, 2400000.0, 0.20));
        System.out.println(runWaterfall(11000000.0, 10000000.0, 2400000.0, 0.20));
    }
}