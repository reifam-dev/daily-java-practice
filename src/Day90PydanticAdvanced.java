import java.util.ArrayList;
import java.util.Arrays;

/**
 * Day 90 – Pydantic advanced concepts in Java: validated models, inheritance, settings.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day90PydanticAdvanced {

    private String sector;
    private String region;
    private double value;
    private double yieldPct;
    private double capitalValue;

    private static final ArrayList<String> ALLOWED_SECTORS = new ArrayList<>(
            Arrays.asList("Office", "Retail", "Industrial")
    );

    public Day90PydanticAdvanced(String sector, String region, double value, double yieldPct) {
        validateSector(sector);
        validatePositive("value", value);
        validatePositive("yieldPct", yieldPct);
        validateYield(yieldPct);
        this.sector = sector;
        this.region = region;
        this.value = value;
        this.yieldPct = yieldPct;
        this.capitalValue = computeCV();
    }

    public String getSector() { return this.sector; }
    public String getRegion() { return this.region; }
    public double getValue() { return this.value; }
    public double getYieldPct() { return this.yieldPct; }
    public double getCapitalValue() { return this.capitalValue; }

    private void validateSector(String sector) {
        if (!ALLOWED_SECTORS.contains(sector)) {
            throw new IllegalArgumentException(
                    "Sector must be one of " + ALLOWED_SECTORS + ". Got '" + sector + "'."
            );
        }
    }

    private void validatePositive(String field, double value) {
        if (value <= 0) {
            throw new IllegalArgumentException(
                    field + " must be positive. Got " + value + "."
            );
        }
    }

    private void validateYield(double yieldPct) {
        if (yieldPct > 20.0) {
            throw new IllegalArgumentException(
                    "yieldPct must be below 20%. Got " + yieldPct + "."
            );
        }
    }

    private double computeCV() {
        return this.value / (this.yieldPct / 100.0);
    }

    public double netInitialYield() {
        return (this.value / this.capitalValue) * 100;
    }

    // Settings inner-class equivalent
    public static class PortfolioSettings {

        private final int maxDeals;
        private final double maxSingleExposure;
        private final ArrayList<String> allowedSectors;

        public PortfolioSettings(int maxDeals, double maxSingleExposure,
                                 ArrayList<String> allowedSectors) {
            if (maxDeals <= 0) throw new IllegalArgumentException("maxDeals must be positive.");
            if (maxSingleExposure <= 0 || maxSingleExposure > 1.0) {
                throw new IllegalArgumentException("maxSingleExposure must be in (0, 1].");
            }
            this.maxDeals = maxDeals;
            this.maxSingleExposure = maxSingleExposure;
            this.allowedSectors = allowedSectors;
        }

        public double exposureLimit(double totalPortfolioValue) {
            return totalPortfolioValue * this.maxSingleExposure;
        }

        @Override
        public String toString() {
            return String.format(
                    "PortfolioSettings | maxDeals=%d | maxExposure=%.0f%% | sectors=%s",
                    this.maxDeals, this.maxSingleExposure * 100, this.allowedSectors
            );
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Deal | sector=%s | region=%s | value=£%.1fm | yield=%.2f%% | CV=£%.1fm",
                this.sector, this.region, this.value, this.yieldPct, this.capitalValue
        );
    }

    public static void main(String[] args) {
        System.out.println("=== Individual deals ===");
        Day90PydanticAdvanced[] deals = {
                new Day90PydanticAdvanced("Office",     "London",     80.0, 4.5),
                new Day90PydanticAdvanced("Retail",     "Manchester", 30.0, 5.5),
                new Day90PydanticAdvanced("Industrial", "Birmingham", 60.0, 5.0),
        };
        for (Day90PydanticAdvanced d : deals) System.out.println("  " + d);

        System.out.println("\n=== Portfolio settings ===");
        ArrayList<String> sectors = new ArrayList<>(Arrays.asList("Office", "Industrial"));
        PortfolioSettings settings = new PortfolioSettings(30, 0.20, sectors);
        System.out.println("  " + settings);
        System.out.printf("  Exposure limit on £500m portfolio: £%.1fm%n",
                settings.exposureLimit(500));

        System.out.println("\n=== Validation errors ===");
        try {
            new Day90PydanticAdvanced("Residential", "London", 50.0, 4.0);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
        }

        try {
            new Day90PydanticAdvanced("Office", "London", 50.0, 25.0);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
        }

        try {
            new Day90PydanticAdvanced("Office", "London", -10.0, 4.5);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
        }
    }
}