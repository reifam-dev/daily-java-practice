import java.util.HashMap;
import java.util.Map;

class DealLookupTool {
    private Map<String, Double> ltvByDeal;

    public DealLookupTool() {
        ltvByDeal = new HashMap<>();
        ltvByDeal.put("riverside-jv", 0.60);
        ltvByDeal.put("logistics-portfolio", 0.55);
    }

    public Double getLtv(String dealId) {
        return ltvByDeal.get(dealId)
    }
}

public class Day103ErrorQuiz {

    private static String describeLtvRisk(double ltv) {
        if (ltv > 0.65) {
            return "high risk";
        } else if (ltv > 0.55) {
            return "moderate risk";
        }
        return "low risk"
    }

    public static void main(String[] args) {
        DealLookupTool tool = new DealLookupTool();
        String dealId = "riverside-jv";
        Double ltv = tool.getLtv(dealId);

        if (ltv = null) {
            System.out.println("Deal not found: " + dealId);
            return;
        }

        System.out.println(dealId + " LTV: " + ltv + " (" + describeLtvRisk(ltv) + ")");
    }
}