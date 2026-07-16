import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class ValuationFetchException extends RuntimeException {
    public ValuationFetchException(String message) {
        super(message);
    }
}

/**
 * Fetches multiple deal valuations concurrently using async HttpClient
 * and CompletableFuture.allOf, bounded by an overall timeout.
 */
public class Day97AsyncDealsFetcher {

    private static final long OVERALL_TIMEOUT_SECONDS = 10L;

    private final HttpClient client;
    private final List<String> dealSources;

    public Day97AsyncDealsFetcher(List<String> dealSources) {
        this.client = HttpClient.newHttpClient();
        this.dealSources = dealSources;
    }

    private CompletableFuture<String> fetchValuation(String url) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        return this.client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public List<String> fetchAllValuations()
            throws InterruptedException, ExecutionException, TimeoutException {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (String url : this.dealSources) {
            futures.add(fetchValuation(url));
        }

        CompletableFuture<Void> allDone = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0]));
        allDone.get(OVERALL_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        List<String> results = new ArrayList<>();
        for (CompletableFuture<String> future : futures) {
            results.add(future.get());
        }
        return results;
    }

    @Override
    public String toString() {
        return "Day97AsyncDealsFetcher{dealSources=" + this.dealSources.size() + '}';
    }

    public static void main(String[] args) {
        List<String> sources = List.of(
                "https://api.example.com/valuations/riverside-jv",
                "https://api.example.com/valuations/logistics-portfolio"
        );
        Day97AsyncDealsFetcher fetcher = new Day97AsyncDealsFetcher(sources);
        try {
            List<String> results = fetcher.fetchAllValuations();
            System.out.println(results);
        } catch (TimeoutException e) {
            throw new ValuationFetchException("Valuation sources timed out");
        } catch (InterruptedException | ExecutionException e) {
            throw new ValuationFetchException("Valuation fetch failed: " + e.getMessage());
        }
    }
}