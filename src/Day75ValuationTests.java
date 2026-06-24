/**
 * Day 75 – pytest concepts in Java: manual assertions and test-style methods.
 * 1Z0-811 standard: private fields, this keyword, getters, toString override.
 */
public class Day75ValuationTests {

    private double noi;
    private double capRate;

    public Day75ValuationTests(double noi, double capRate) {
        if (capRate <= 0) {
            throw new IllegalArgumentException("Capitalisation rate must be positive.");
        }
        this.noi = noi;
        this.capRate = capRate;
    }

    public double getNoi() { return this.noi; }
    public double getCapRate() { return this.capRate; }

    public double capitalValue() {
        return this.noi / this.capRate;
    }

    public double reversionaryYield(double erv) {
        return (erv / capitalValue()) * 100;
    }

    public boolean isAboveThreshold(double threshold) {
        return capitalValue() > threshold;
    }

    @Override
    public String toString() {
        return String.format(
                "Valuation | NOI=£%.0f | CapRate=%.2f%% | CV=£%.0f",
                this.noi, this.capRate * 100, capitalValue()
        );
    }

    public static void runTests() {
        System.out.println("Running tests...");

        Day75ValuationTests v1 = new Day75ValuationTests(100_000, 0.05);
        assert v1.capitalValue() == 2_000_000 : "Test 1 failed";
        System.out.println("PASS: capital value at 5%");

        Day75ValuationTests v2 = new Day75ValuationTests(200_000, 0.04);
        assert v2.capitalValue() == 5_000_000 : "Test 2 failed";
        System.out.println("PASS: capital value at 4%");

        assert Math.round(v1.reversionaryYield(110_000) * 100.0) / 100.0 == 5.5 : "Test 3 failed";
        System.out.println("PASS: reversionary yield");

        try {
            new Day75ValuationTests(100_000, 0.0);
            System.out.println("FAIL: should have thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: zero cap rate raises exception");
        }

        System.out.println("All tests passed.");
    }

    public static void main(String[] args) {
        Day75ValuationTests v = new Day75ValuationTests(120_000, 0.06);
        System.out.println(v);
        System.out.printf("Reversionary yield: %.2f%%%n", v.reversionaryYield(130_000));
        runTests();
    }
}