// This file contains 3 deliberate bugs. Find and fix them.
public class Day73ErrorQuiz {

    private String baseUrl;
    private String apiKey;

    public Day73ErrorQuiz(String baseUrl, String apiKey) {
        baseUrl = baseUrl;                      // Bug 1: missing this
        this.apiKey = apiKey;
    }

    public String buildUrl(String endpoint) {
        return this.baseUrl + "/" + endpoint;
    }

    public double parseYield(String raw) {
        double value = Double.parseDouble(raw);
        return value == 100.0;                  // Bug 2: == should be / (should return value / 100.0)
    }

    public void printEndpoints(String[] endpoints) {
        for (String ep : endpoints) {
            System.out.println(buildUrl(ep))
        }                                       // Bug 3: missing semicolon
    }

    @Override
    public String toString() {
        return "Client: " + baseUrl + " | Key: " + apiKey;
    }

    public static void main(String[] args) {
        Day73ErrorQuiz client = new Day73ErrorQuiz("https://api.example.com", "abc123");
        System.out.println(client.buildUrl("properties/1"));
        System.out.println(client.parseYield("5.5"));
        client.printEndpoints(new String[]{"properties", "deals", "valuations"});
    }
}