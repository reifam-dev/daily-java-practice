// This file contains 3 deliberate bugs. Find and fix them.
import java.util.HashMap;

public class Day88ErrorQuiz {

    private static Day88ErrorQuiz instance = null;
    private String configName;
    private HashMap<String, String> registry;

    private Day88ErrorQuiz(String configName) {
        configName = configName;                // Bug 1: missing this
        this.registry = new HashMap<>();
    }

    public static Day88ErrorQuiz getInstance(String configName) {
        if (instance == null) {
            instance = new Day88ErrorQuiz(configName);
        }
        return instance;
    }

    public void register(String name, String value) {
        this.registry.put(name, value);
    }

    public double validatePositive(double value) {
        if (value =< 0) {                       // Bug 2: =< should be <=
            throw new IllegalArgumentException("Value must be positive: " + value);
        }
        return value;
    }

    public void printRegistry() {
        for (String key : this.registry.keySet()) {
            System.out.println(key + " -> " + this.registry.get(key))
        }                                       // Bug 3: missing semicolon
    }

    @Override
    public String toString() {
        return "Config: " + configName + " | entries=" + registry.size();
    }

    public static void main(String[] args) {
        Day88ErrorQuiz c1 = Day88ErrorQuiz.getInstance("primary");
        Day88ErrorQuiz c2 = Day88ErrorQuiz.getInstance("secondary");
        c1.register("OfficeAsset", "sector=Office");
        c1.register("RetailAsset", "sector=Retail");
        System.out.println(c1);
        System.out.println("Same instance: " + (c1 == c2));
        c1.printRegistry();
    }
}