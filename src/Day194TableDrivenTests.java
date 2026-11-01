/**
 * A hand-rolled table-driven test harness (no framework needed). One
 * loop runs every row of the case table - the plain-Java equivalent of
 * pytest.mark.parametrize. Doubles are compared with a tolerance, and
 * the expected-exception helper FAILS if nothing was thrown.
 */
public class Day194TableDrivenTests {

    private static final double TOLERANCE = 1e-6;
    private static int failures = 0;

    private static double[] splitResidual(double remaining, double gpPct) {
        if (remaining < 0.0) {
            throw new IllegalArgumentException("remaining must be non-negative");
        }
        if (gpPct < 0.0 || gpPct > 1.0) {
            throw new IllegalArgumentException("gpPct must be between 0 and 1");
        }
        double gp = remaining * gpPct;
        return new double[]{remaining - gp, gp};
    }

    private static boolean approx(double actual, double expected) {
        return Math.abs(actual - expected) < TOLERANCE;
    }

    private static void check(String name, boolean condition) {
        if (condition) {
            System.out.println("PASS " + name);
        } else {
            failures++;
            System.out.println("FAIL " + name);
        }
    }

    private static void expectIllegalArgument(String name, double remaining, double pct) {
        try {
            splitResidual(remaining, pct);
        } catch (IllegalArgumentException e) {
            check(name, true);
            return;
        }
        check(name + " (expected IllegalArgumentException, none thrown)", false);
    }

    public static void main(String[] args) {
        double[][] cases = {
                {2600000.0, 0.20, 2080000.0, 520000.0},
                {1.1, 0.10, 0.99, 0.11},
                {0.0, 0.20, 0.0, 0.0},
                {1000.0, 0.0, 1000.0, 0.0},
                {1000.0, 1.0, 0.0, 1000.0}};

        for (double[] c : cases) {
            double[] result = splitResidual(c[0], c[1]);
            check("split " + c[0] + " at " + c[1],
                    approx(result[0], c[2]) && approx(result[1], c[3]));
        }

        expectIllegalArgument("rejects pct above 1", 1000.0, 1.5);
        expectIllegalArgument("rejects negative pct", 1000.0, -0.1);
        expectIllegalArgument("rejects negative remaining", -1.0, 0.20);

        System.out.println("failures=" + failures);
        System.exit(failures == 0 ? 0 : 1);
    }
}