// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Day89ErrorQuiz {

    private String baseUrl;
    private ArrayList<String> fetchLog;

    public Day89ErrorQuiz(String baseUrl) {
        baseUrl = baseUrl;                      // Bug 1: missing this
        this.fetchLog = new ArrayList<>();
    }

    public CompletableFuture<String> fetchYield(String sector) {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            return sector + ": 4.5%";
        });
    }

    public double parseYield(String raw) {
        String numeric = raw.split(": ")[1].replace("%", "");
        return Double.parseDouble(numeric) =+ 100;   // Bug 2: =+ should not be here — return Double.parseDouble(numeric)
    }

    public void logFetch(String sector) {
        this.fetchLog.add("Fetched: " + sector)
    }                                           // Bug 3: missing semicolon

    @Override
    public String toString() {
        return "Fetcher | baseUrl=" + baseUrl + " | fetches=" + fetchLog.size();
    }

    public static void main(String[] args) throws Exception {
        Day89ErrorQuiz fetcher = new Day89ErrorQuiz("https://api.example.com");
        CompletableFuture<String> f = fetcher.fetchYield("Office");
        String result = f.get();
        System.out.println(result);
        fetcher.logFetch("Office");
        System.out.println(fetcher);
    }
}