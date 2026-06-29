// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;

public class Day80ErrorQuiz {

    private String modelName;
    private ArrayList<String> tools;

    public Day80ErrorQuiz(String modelName) {
        modelName = modelName;                  // Bug 1: missing this
        this.tools = new ArrayList<>();
    }

    public void addTool(String toolName) {
        this.tools.add(toolName);
    }

    public double calculateValue(double noi, double yieldPct) {
        return noi =+ (yieldPct / 100);        // Bug 2: =+ should be / (capital value = noi / (yieldPct/100))
    }

    public void printTools() {
        for (String tool : this.tools) {
            System.out.println("Tool: " + tool)
        }                                       // Bug 3: missing semicolon
    }

    @Override
    public String toString() {
        return "Agent | model=" + modelName + " | tools=" + tools.size();
    }

    public static void main(String[] args) {
        Day80ErrorQuiz agent = new Day80ErrorQuiz("claude-sonnet-4-6");
        agent.addTool("get_yield");
        agent.addTool("calculate_value");
        System.out.println(agent);
        agent.printTools();
        System.out.println("CV: " + agent.calculateValue(100_000, 4.5));
    }
}