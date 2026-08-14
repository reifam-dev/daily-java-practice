import java.util.UUID;
import java.util.logging.Logger;

public class Day126ErrorQuiz {

    private static final Logger LOGGER = Logger.getLogger("deal_pipeline");

    private static UUID startRequest() {
        UUID correlationId = UUID.randomUUID();
        return correlationId;
    }

    private static double fetchDeal(String dealName) {
        LOGGER.info("Fetching deal: " + dealName);
        return 12500000.0;
    }

    private static double scoreDeal(String dealName, double marketValue) {
        LOGGER.info("Scoring deal: " + dealName)
        return marketValue * 0.05;
    }

    private static String processDealRequest(String dealName) {
        UUID correlationId = startRequest();
        double marketValue = fetchDeal(dealName);
        double score = scoreDeal(dealName, marketValue);
        return dealName + " scored " + score + " [" + correlationId + "]";
    }

    public static void main(String[] args) {
        System.out.println(processDealRequest("Riverside JV"));
    }
}