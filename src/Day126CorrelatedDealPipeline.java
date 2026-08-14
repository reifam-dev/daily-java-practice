import java.util.UUID;
import java.util.logging.Logger;

/**
 * Generates a single correlation id per request and threads it
 * through every log line produced while handling that request - a
 * Java analogue of the Python correlated logging pattern.
 */
public class Day126CorrelatedDealPipeline {

    private static final Logger LOGGER = Logger.getLogger("deal_pipeline");

    private static String startRequest() {
        return UUID.randomUUID().toString();
    }

    private static double fetchDeal(String dealName, String correlationId) {
        LOGGER.info("[" + correlationId + "] Fetching deal: " + dealName);
        return 12500000.0;
    }

    private static double scoreDeal(String dealName, double marketValue, String correlationId) {
        LOGGER.info("[" + correlationId + "] Scoring deal: " + dealName);
        return marketValue * 0.05;
    }

    private static String processDealRequest(String dealName) {
        String correlationId = startRequest();
        double marketValue = fetchDeal(dealName, correlationId);
        double score = scoreDeal(dealName, marketValue, correlationId);
        return dealName + " scored " + score + " [" + correlationId + "]";
    }

    public static void main(String[] args) {
        String result1 = processDealRequest("Riverside JV");
        String result2 = processDealRequest("Westgate Retail");
        System.out.println(result1);
        System.out.println(result2);
    }
}