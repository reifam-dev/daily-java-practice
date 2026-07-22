import java.util.HashMap;
import java.util.Map;

/**
 * In-memory simulation of a tool a model agent might call: given a
 * deal id, returns its LTV, or null if the deal doesn't exist.
 */
final class DealLookupTool {
    private final Map<String, Double> ltvByDeal;

    public DealLookupTool() {
        this.ltvByDeal = new HashMap<>();
        this.ltvByDeal.put("riverside-jv", 0.60);
        this.ltvByDeal.put("logistics-portfolio", 0.55);
    }

    public Double getLtv(String dealId) {
        return this.ltvByDeal.get(dealId);
    }
}

public class Day103DealToolAgent {

    private static String describeLtvRisk(double ltv) {
        if (ltv > 0.65) {
            return "high risk";
        } else if (ltv > 0.55) {
            return "moderate risk";
        }
        return "low risk";
    }

    public static void main(String[] args) {
        DealLookupTool tool = new DealLookupTool();
        String dealId = "riverside-jv";
        Double ltv = tool.getLtv(dealId);

        if (ltv == null) {
            System.out.println("Deal not found: " + dealId);
            return;
        }

        System.out.println(dealId + " LTV: " + ltv + " (" + describeLtvRisk(ltv) + ")");
    }
}