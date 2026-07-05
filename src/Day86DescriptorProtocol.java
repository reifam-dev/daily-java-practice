/**
 * Day 86 – Descriptor Protocol concepts in Java: validated setters, read-only fields,
 * type enforcement via encapsulation and custom setter logic.
 * 1Z0-811 standard: private fields, this keyword, getters, toString override.
 */
public class Day86DescriptorProtocol {

    private String sector;
    private double value;
    private double yieldPct;
    private final String assetClass;            // final = read-only equivalent

    public Day86DescriptorProtocol(String sector, double value, double yieldPct) {
        this.assetClass = "Commercial Real Estate";
        setSector(sector);
        setValue(value);
        setYieldPct(yieldPct);
    }

    public String getSector() { return this.sector; }
    public double getValue() { return this.value; }
    public double getYieldPct() { return this.yieldPct; }
    public String getAssetClass() { return this.assetClass; }

    public void setSector(String sector) {
        if (sector == null || sector.isBlank()) {
            throw new IllegalArgumentException("Sector must not be blank.");
        }
        this.sector = sector;
    }

    public void setValue(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException(
                    "value must be positive. Got " + value + "."
            );
        }
        this.value = value;
    }

    public void setYieldPct(double yieldPct) {
        if (yieldPct <= 0) {
            throw new IllegalArgumentException(
                    "yield_pct must be positive. Got " + yieldPct + "."
            );
        }
        this.yieldPct = yieldPct;
    }

    public double capitalValue() {
        return this.value / (this.yieldPct / 100.0);
    }

    public void printDescriptors() {
        System.out.println("=== Descriptor State ===");
        System.out.printf("Sector      : %s%n", this.sector);
        System.out.printf("Value       : £%.1fm%n", this.value);
        System.out.printf("Yield       : %.2f%%%n", this.yieldPct);
        System.out.printf("Capital CV  : £%.1fm%n", capitalValue());
        System.out.printf("Asset class : %s (read-only)%n", this.assetClass);
    }

    @Override
    public String toString() {
        return String.format(
                "PropertyDeal | sector=%s | value=£%.1fm | yield=%.2f%% | CV=£%.1fm",
                this.sector, this.value, this.yieldPct, capitalValue()
        );
    }

    public static void main(String[] args) {
        Day86DescriptorProtocol deal =
                new Day86DescriptorProtocol("Office", 80.0, 4.5);
        deal.printDescriptors();

        System.out.println("\nUpdating value to £90m...");
        deal.setValue(90.0);
        System.out.println(deal);

        System.out.println("\nTesting validated setters:");
        try {
            deal.setValue(-10.0);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
        }

        try {
            deal.setYieldPct(0.0);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
        }

        try {
            deal.setSector("");
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
        }
    }
}