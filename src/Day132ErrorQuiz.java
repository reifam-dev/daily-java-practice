import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day132ErrorQuiz {

    private static Map<String, String> investors = new HashMap<>();
    private static Map<String, String> deals = new HashMap<>();

    private static String createInvestor(String name) {
        String investorId = "inv-" + (investors.size() + 1);
        investors.put(investorId, name);
        return investorId;
    }

    private static void undoCreateInvestor(String investorId) {
        investors.remove(investorId);
    }

    private static String createDeal(String investorId, String dealName) {
        String dealId = "deal-" + (deals.size() + 1);
        deals.put(dealId, dealName);
        return dealId;
    }

    private static void sendConfirmation(String dealId) {
        if (dealId.equals("deal-1")) {
            throw new RuntimeException("Confirmation service unavailable");
        }
        System.out.println("Confirmation sent for " + dealId);
    }

    private static Map<String, String> onboardInvestorWithDeal(String name, String dealName) {
        List<String[]> completedSteps = new ArrayList<>();

        String investorId = createInvestor(name);
        completedSteps.add(new String[]{"investor", investorId});

        String dealId = createDeal(investorId, dealName);
        completedSteps.add(new String[]{"deal", dealId});

        sendConfirmation(dealId);

        Map<String, String> result = new HashMap<>();
        result.put("investorId", investorId);
        result.put("dealId", dealId);
        return result
    }

    public static void main(String[] args) {
        Map<String, String> result = onboardInvestorWithDeal("Fund A", "Riverside JV");
        System.out.println(result);
    }
}