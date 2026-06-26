import java.util.ArrayList;

/**
 * Day 78 – LangChain concepts in Java: prompt building, chain simulation, history logging.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day78PropertyReportChain {

    private String modelName;
    private String systemPrompt;
    private ArrayList<String[]> promptHistory;

    public Day78PropertyReportChain(String modelName, String systemPrompt) {
        this.modelName = modelName;
        this.systemPrompt = systemPrompt;
        this.promptHistory = new ArrayList<>();
    }

    public String getModelName() { return this.modelName; }
    public int getHistorySize() { return this.promptHistory.size(); }

    public String buildPrompt(String userMessage) {
        return "SYSTEM: " + this.systemPrompt + "\nHUMAN: " + userMessage;
    }

    public void logExchange(String userMessage, String assistantReply) {
        this.promptHistory.add(new String[]{"human", userMessage});
        this.promptHistory.add(new String[]{"assistant", assistantReply});
    }

    public ArrayList<String[]> getHistoryByRole(String role) {
        ArrayList<String[]> result = new ArrayList<>();
        for (String[] entry : this.promptHistory) {
            if (entry[0].equals(role)) result.add(entry);
        }
        return result;
    }

    public void printHistory() {
        System.out.println("=== Prompt History ===");
        for (int i = 0; i < this.promptHistory.size(); i++) {
            String[] entry = this.promptHistory.get(i);
            System.out.printf("[%d] %s: %s%n", i + 1, entry[0].toUpperCase(), entry[1]);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "PropertyReportChain | model=%s | exchanges=%d",
                this.modelName, this.promptHistory.size() / 2
        );
    }

    public static void main(String[] args) {
        Day78PropertyReportChain chain = new Day78PropertyReportChain(
                "claude-sonnet-4-6",
                "You are a RICS-qualified property analyst. Respond in British English."
        );

        chain.logExchange(
                "Write an investment summary for an EC2 office at 5% NIY.",
                "The asset offers stable income with strong covenant quality in a prime City location."
        );
        chain.logExchange(
                "What are the key risks?",
                "Key risks include lease expiry in 3 years, rising vacancy rates, and rate sensitivity."
        );

        chain.printHistory();
        System.out.println();
        System.out.println("Human messages: " + chain.getHistoryByRole("human").size());
        System.out.println(chain);
    }
}