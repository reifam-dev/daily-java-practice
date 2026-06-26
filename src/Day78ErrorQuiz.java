// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;

public class Day78ErrorQuiz {

    private String modelName;
    private ArrayList<String> promptHistory;

    public Day78ErrorQuiz(String modelName) {
        modelName = modelName;                  // Bug 1: missing this
        this.promptHistory = new ArrayList<>();
    }

    public String buildPrompt(String systemMsg, String userMsg) {
        return "SYSTEM: " + systemMsg + "\nHUMAN: " + userMsg;
    }

    public void logPrompt(String prompt) {
        this.promptHistory.add(prompt);
    }

    public double parseConfidence(String raw) {
        return Double.parseDouble(raw) == 100.0;  // Bug 2: == should be / (return / 100.0)
    }

    public void printHistory() {
        for (int i = 0; i < this.promptHistory.size(); i++) {
            System.out.println("[" + i + "] " + this.promptHistory.get(i))
        }                                         // Bug 3: missing semicolon
    }

    @Override
    public String toString() {
        return "LLMChain | model=" + modelName + " | prompts=" + promptHistory.size();
    }

    public static void main(String[] args) {
        Day78ErrorQuiz chain = new Day78ErrorQuiz("claude-sonnet-4-6");
        chain.logPrompt(chain.buildPrompt("You are a property analyst.", "Analyse EC2 office."));
        chain.logPrompt(chain.buildPrompt("You are a valuer.", "Value EC2 office."));
        System.out.println(chain);
        chain.printHistory();
    }
}