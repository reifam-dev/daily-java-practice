import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Fans valuation calls out to a thread pool. The pool is ALWAYS shut
 * down (non-daemon pool threads would otherwise keep the JVM alive
 * forever), each result is fetched with a timeout, and one task's
 * failure is recorded per deal instead of aborting the whole batch.
 */
public class Day186ParallelValuation {

    private static double valueDeal(String dealId) {
        if (dealId.equals("deal-3")) {
            throw new IllegalArgumentException("No valuation data for " + dealId);
        }
        return dealId.length() * 1000000.0;
    }

    private static void valueAll(List<String> dealIds, Map<String, Double> results,
                                 Map<String, String> errors) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            List<Future<Double>> futures = new ArrayList<>();
            for (String dealId : dealIds) {
                Callable<Double> task = () -> valueDeal(dealId);
                futures.add(executor.submit(task));
            }

            for (int i = 0; i < dealIds.size(); i++) {
                String dealId = dealIds.get(i);
                try {
                    results.put(dealId, futures.get(i).get(5, TimeUnit.SECONDS));
                } catch (ExecutionException e) {
                    errors.put(dealId, e.getCause().getMessage());
                } catch (TimeoutException e) {
                    errors.put(dealId, "timed out");
                }
            }
        } finally {
            executor.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Map<String, Double> results = new LinkedHashMap<>();
        Map<String, String> errors = new LinkedHashMap<>();
        valueAll(List.of("deal-1", "deal-3", "deal-2"), results, errors);
        System.out.println(results);
        System.out.println(errors);
    }
}