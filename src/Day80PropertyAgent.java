import java.util.ArrayList;

/**
 * Day 80 – AI Agent concepts in Java: tool registry, ReAct loop simulation, tool execution.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day80PropertyAgent {

    private String modelName;
    private ArrayList<String[]> tools;          // [name, description]
    private ArrayList<String[]> messageHistory; // [role, content]

    public Day80PropertyAgent(String modelName) {
        this.modelName = modelName;
        this.tools = new ArrayList<>();
        this.messageHistory = new ArrayList<>();
        registerDefaultTools();
    }

    public String getModelName() { return this.modelName; }
    public int getToolCount() { return this.tools.size(); }

    private void registerDefaultTools() {
        this.tools.add(new String[]{"get_yield", "Returns market yield for a property sector."});
        this.tools.add(new String[]{"calculate_value", "Calculates capital value from NOI and yield."});
    }

    public void addTool(String name, String description) {
        this.tools.add(new String[]{name, description});
    }

    public String executeTool(String name, String sector, double noi, double yieldPct) {
        if (name.equals("get_yield")) {
            if (sector.equals("Office"))     return "4.5%";
            if (sector.equals("Retail"))     return "5.5%";
            if (sector.equals("Industrial")) return "5.0%";
            return "5.0%";
        }
        if (name.equals("calculate_value")) {
            double value = noi / (yieldPct / 100.0);
            return String.format("£%.0f", value);
        }
        return "Unknown tool: " + name;
    }

    public void logMessage(String role, String content) {
        this.messageHistory.add(new String[]{role, content});
    }

    public double calculateValue(double noi, double yieldPct) {
        return noi / (yieldPct / 100.0);
    }

    public void printTools() {
        System.out.println("Available tools:");
        for (String[] tool : this.tools) {
            System.out.printf("  [%s] %s%n", tool[0], tool[1]);
        }
    }

    public void printHistory() {
        for (String[] msg : this.messageHistory) {
            System.out.printf("[%s] %s%n", msg[0].toUpperCase(), msg[1]);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "PropertyAgent | model=%s | tools=%d | messages=%d",
                this.modelName, this.tools.size(), this.messageHistory.size()
        );
    }

    public static void main(String[] args) {
        Day80PropertyAgent agent = new Day80PropertyAgent("claude-sonnet-4-6");
        agent.printTools();

        // Simulate ReAct loop
        agent.logMessage("user", "What is the capital value of an Office property with £200,000 NOI?");
        String yield = agent.executeTool("get_yield", "Office", 0, 0);
        agent.logMessage("tool", "get_yield(Office) -> " + yield);
        String value = agent.executeTool("calculate_value", "", 200_000, 4.5);
        agent.logMessage("tool", "calculate_value(200000, 4.5) -> " + value);
        agent.logMessage("assistant", "The capital value is " + value + " based on a 4.5% NIY.");

        System.out.println();
        agent.printHistory();
        System.out.println();
        System.out.println(agent);
        System.out.printf("Manual CV: £%.0f%n", agent.calculateValue(200_000, 4.5));
    }
}