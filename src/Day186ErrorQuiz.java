import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Day186ErrorQuiz {

    private static double valueDeal(String dealId) {
        if (dealId.equals("deal-3")) {
            throw new IllegalArgumentException("No valuation data for " + dealId);
        }
        return dealId.length() * 1000000.0;
    }

    private static Map<String, Double> valueAll(List<String> dealIds) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Double>> futures = new ArrayList<>();
        for (String dealId : dealIds) {
            Callable<Double> task = () -> valueDeal(dealId);
            futures.add(executor.submit(task));
        }

        Map<String, Double> results = new LinkedHashMap<>();
        for (int i = 0; i < dealIds.size(); i++) {
            results.put(dealIds.get(i), futures.get(i).get())
        }
        return results;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(valueAll(List.of("deal-1", "deal-2")));
        System.out.println(valueAll(List.of("deal-1", "deal-3", "deal-2")));
    }
}