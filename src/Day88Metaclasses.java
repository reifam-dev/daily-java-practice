import java.util.HashMap;

/**
 * Day 88 – Metaclass concepts in Java: Singleton pattern, class registry, validated config.
 * 1Z0-811 standard: private fields, this keyword, static instance, HashMap, toString override.
 */
public class Day88Metaclasses {

    // Singleton instance
    private static Day88Metaclasses instance = null;

    private String configName;
    private HashMap<String, String> registry;
    private HashMap<String, Double> numericConfig;

    private Day88Metaclasses(String configName) {
        this.configName = configName;
        this.registry = new HashMap<>();
        this.numericConfig = new HashMap<>();
    }

    public static Day88Metaclasses getInstance(String configName) {
        if (instance == null) {
            instance = new Day88Metaclasses(configName);
        }
        return instance;
    }

    public String getConfigName() { return this.configName; }
    public int getRegistrySize() { return this.registry.size(); }

    public void register(String name, String value) {
        this.registry.put(name, value);
    }

    public void setNumericConfig(String key, double value) {
        if (value < 0) {
            throw new IllegalArgumentException(
                    "Negative config value not permitted: " + key + "=" + value
            );
        }
        this.numericConfig.put(key, value);
    }

    public double getNumericConfig(String key, double defaultValue) {
        return this.numericConfig.getOrDefault(key, defaultValue);
    }

    public boolean validatePositive(double value) {
        return value > 0;
    }

    public void printRegistry() {
        System.out.println("=== Asset Registry ===");
        for (String key : this.registry.keySet()) {
            System.out.printf("  %-20s -> %s%n", key, this.registry.get(key));
        }
    }

    public void printNumericConfig() {
        System.out.println("=== Numeric Config ===");
        for (String key : this.numericConfig.keySet()) {
            System.out.printf("  %-20s = %.4f%n", key, this.numericConfig.get(key));
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Metaclasses | config=%s | registry=%d | numericConfig=%d",
                this.configName, this.registry.size(), this.numericConfig.size()
        );
    }

    public static void main(String[] args) {
        Day88Metaclasses c1 = Day88Metaclasses.getInstance("PropertyFund");
        Day88Metaclasses c2 = Day88Metaclasses.getInstance("AnotherFund");

        c1.register("OfficeAsset", "sector=Office, yield=4.5%");
        c1.register("RetailAsset", "sector=Retail, yield=5.5%");
        c1.register("IndustrialAsset", "sector=Industrial, yield=5.0%");

        c1.setNumericConfig("target_return", 0.08);
        c1.setNumericConfig("max_ltv", 0.65);
        c1.setNumericConfig("min_yield", 0.045);

        System.out.println("Same instance: " + (c1 == c2));
        c1.printRegistry();
        c1.printNumericConfig();

        try {
            c1.setNumericConfig("bad_value", -0.05);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        System.out.println("\n" + c1);
    }
}