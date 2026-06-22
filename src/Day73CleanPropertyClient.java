import java.util.ArrayList;

/**
 * Day 73 – REST API concepts in Java: URL building, response parsing, error handling.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day73CleanPropertyClient {

    private String baseUrl;
    private String apiKey;
    private ArrayList<String> requestLog;

    public Day73CleanPropertyClient(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.requestLog = new ArrayList<>();
    }

    public String getBaseUrl() { return this.baseUrl; }
    public String getApiKey() { return this.apiKey; }
    public ArrayList<String> getRequestLog() { return this.requestLog; }

    public String buildUrl(String endpoint) {
        return this.baseUrl + "/" + endpoint;
    }

    public void logRequest(String method, String endpoint) {
        this.requestLog.add(method.toUpperCase() + " " + buildUrl(endpoint));
    }

    public double parseYield(String raw) {
        return Double.parseDouble(raw) / 100.0;
    }

    public boolean isValidStatusCode(int code) {
        return code >= 200 && code < 300;
    }

    public void printRequestLog() {
        for (int i = 0; i < this.requestLog.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, this.requestLog.get(i));
        }
    }

    @Override
    public String toString() {
        return String.format(
                "PropertyClient | baseUrl=%s | requests=%d",
                this.baseUrl, this.requestLog.size()
        );
    }

    public static void main(String[] args) {
        Day73CleanPropertyClient client =
                new Day73CleanPropertyClient("https://api.example.com", "abc123");

        client.logRequest("GET", "properties/1");
        client.logRequest("POST", "deals");
        client.logRequest("DELETE", "deals/5");

        client.printRequestLog();
        System.out.printf("Yield parsed: %.4f%n", client.parseYield("5.75"));
        System.out.println("200 valid? " + client.isValidStatusCode(200));
        System.out.println("404 valid? " + client.isValidStatusCode(404));
        System.out.println(client);
    }
}