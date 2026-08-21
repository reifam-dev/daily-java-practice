import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class SagaFailedException extends RuntimeException {
    public SagaFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Runs a multi-step onboarding process and, if any step fails
 * partway through, compensates by undoing the steps that already
 * succeeded, in reverse order.
 */
public class Day132InvestorOnboardingSaga {

    private static final Map<String, String> investors = new HashMap<>();
    private static final Map<String, String> deals = new HashMap<>();

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

    private static void undoCreateDeal(String dealId) {
        deals.remove(dealId);
    }

    private static void sendConfirmation(String dealId) {
        if (dealId.equals("deal-1")) {
            throw new RuntimeException("Confirmation service unavailable");
        }
        System.out.println("Confirmation sent for " + dealId);
    }

    public static Map<String, String> onboardInvestorWithDeal(String name, String dealName) {
        List<String[]> completedSteps = new ArrayList<>();

        try {
            String investorId = createInvestor(name);
            completedSteps.add(new String[]{"investor", investorId});

            String dealId = createDeal(investorId, dealName);
            completedSteps.add(new String[]{"deal", dealId});

            sendConfirmation(dealId);

            Map<String, String> result = new HashMap<>();
            result.put("investorId", investorId);
            result.put("dealId", dealId);
            return result;

        } catch (RuntimeException e) {
            List<String[]> reversedSteps = new ArrayList<>(completedSteps);
            Collections.reverse(reversedSteps);

            for (String[] step : reversedSteps) {
                String stepType = step[0];
                String stepId = step[1];
                if (stepType.equals("investor")) {
                    undoCreateInvestor(stepId);
                    System.out.println("Rolled back investor: " + stepId);
                } else if (stepType.equals("deal")) {
                    undoCreateDeal(stepId);
                    System.out.println("Rolled back deal: " + stepId);
                }
            }
            throw new SagaFailedException("Onboarding failed and was rolled back", e);
        }
    }

    public static void main(String[] args) {
        try {
            Map<String, String> result = onboardInvestorWithDeal("Fund A", "Riverside JV");
            System.out.println(result);
        } catch (SagaFailedException e) {
            System.out.println(e.getMessage() + ": " + e.getCause().getMessage());
            System.out.println("investors remaining: " + investors.keySet());
            System.out.println("deals remaining: " + deals.keySet());
        }
    }
}