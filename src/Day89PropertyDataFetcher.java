import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Day 89 – asyncio concepts in Java: CompletableFuture, supplyAsync, allOf, concurrent fetch.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day89PropertyDataFetcher {

    private String baseUrl;
    private ArrayList<String> fetchLog;

    public Day89PropertyDataFetcher(String baseUrl) {
        this.baseUrl = baseUrl;
        this.fetchLog = new ArrayList<>();
    }

    public String getBaseUrl() { return this.baseUrl; }
    public ArrayList<String> getFetchLog() { return this.fetchLog; }

    public CompletableFuture<String> fetchYield(String sector) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            double[] yields = {4.5, 5.5, 5.0};
            String[] sectors = {"Office", "Retail", "Industrial"};
            for (int i = 0; i < sectors.length; i++) {
                if (sectors[i].equals(sector)) return sector + ": " + yields[i] + "%";
            }
            return sector + ": 5.0%";
        });
    }

    public CompletableFuture<String> fetchValuation(int dealId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Deal " + dealId + ": £" + (dealId * 10.0) + "m";
        });
    }

    public ArrayList<String> fetchAllYields(ArrayList<String> sectors)
            throws InterruptedException, ExecutionException {
        ArrayList<CompletableFuture<String>> futures = new ArrayList<>();
        for (String sector : sectors) {
            futures.add(fetchYield(sector));
            this.fetchLog.add("Fetched: " + sector);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        ArrayList<String> results = new ArrayList<>();
        for (CompletableFuture<String> f : futures) {
            results.add(f.get());
        }
        return results;
    }

    public ArrayList<String> fetchPortfolio(ArrayList<Integer> dealIds)
            throws InterruptedException, ExecutionException {
        ArrayList<CompletableFuture<String>> futures = new ArrayList<>();
        for (int id : dealIds) {
            futures.add(fetchValuation(id));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        ArrayList<String> results = new ArrayList<>();
        for (CompletableFuture<String> f : futures) {
            results.add(f.get());
        }
        return results;
    }

    public double parseYield(String raw) {
        String numeric = raw.split(": ")[1].replace("%", "");
        return Double.parseDouble(numeric);
    }

    public void printStats(ArrayList<String> yields) {
        System.out.println("=== Yield Summary ===");
        double total = 0.0;
        for (String y : yields) {
            System.out.println("  " + y);
            total += parseYield(y);
        }
        System.out.printf("  Mean yield: %.2f%%%n", total / yields.size());
    }

    @Override
    public String toString() {
        return String.format(
                "PropertyDataFetcher | baseUrl=%s | fetches=%d",
                this.baseUrl, this.fetchLog.size()
        );
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Day89PropertyDataFetcher fetcher =
                new Day89PropertyDataFetcher("https://api.example.com");

        ArrayList<String> sectors = new ArrayList<>();
        sectors.add("Office");
        sectors.add("Retail");
        sectors.add("Industrial");

        System.out.println("=== Concurrent yield fetch ===");
        long start = System.currentTimeMillis();
        ArrayList<String> yields = fetcher.fetchAllYields(sectors);
        long elapsed = System.currentTimeMillis() - start;
        fetcher.printStats(yields);
        System.out.printf("  Time: %dms%n%n", elapsed);

        System.out.println("=== Portfolio valuations ===");
        ArrayList<Integer> dealIds = new ArrayList<>();
        for (int i = 1; i <= 5; i++) dealIds.add(i);
        ArrayList<String> valuations = fetcher.fetchPortfolio(dealIds);
        for (String v : valuations) System.out.println("  " + v);

        System.out.println();
        System.out.println(fetcher);
    }
}