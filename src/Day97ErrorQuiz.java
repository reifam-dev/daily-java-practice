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

class ValuationFetchException extends RuntimeException {
    public ValuationFetchException(String message) {
        super(message);
    }
}

public class Day97ErrorQuiz {
    private HttpClient client;
    private List<String> dealSources;

    public Day97ErrorQuiz(List<String> dealSources) {
        client = HttpClient.newHttpClient();
        this.dealSources = dealSources;
    }

    private CompletableFuture<String> fetchValuation(String url) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public List<String> fetchAllValuations() throws InterruptedException, ExecutionException, TimeoutException {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (String url : dealSources) {
            futures.add(fetchValuation(url));
        }

        CompletableFuture<Void> allDone = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0]));
        allDone.get(10, TimeUnit.SECONDS);

        List<String> results = new ArrayList<>();
        for (CompletableFuture<String> future : futures) {
            results.add(future.get())
        }
        return results;
    }

    public static void main(String[] args) throws Exception {
        List<String> sources = List.of(
                "https://api.example.com/valuations/riverside-jv",
                "https://api.example.com/valuations/logistics-portfolio"
        );
        Day97ErrorQuiz fetcher = new Day97ErrorQuiz(sources);
        try {
            List<String> results = fetcher.fetchAllValuations();
            System.out.println(results);
        } catch (TimeoutException e) {
            throw new ValuationFetchException("Valuation sources timed out");
        }
    }
}