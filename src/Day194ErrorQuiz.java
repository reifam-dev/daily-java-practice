public class Day194ErrorQuiz {

    private static int failures = 0;

    private static double[] splitResidual(double remaining, double gpPct) {
        if (gpPct < 0.0 || gpPct > 1.0) {
            throw new IllegalArgumentException("gpPct must be between 0 and 1");
        }
        double gp = remaining * gpPct;
        return new double[]{remaining - gp, gp};
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
        }
    }

    public static void main(String[] args) {
        double[][] cases = {
                {2600000.0, 0.20, 2080000.0, 520000.0},
                {1.1, 0.10, 0.99, 0.11},
                {0.0, 0.20, 0.0, 0.0}};

        for (double[] c : cases) {
            double[] result = splitResidual(c[0], c[1]);
            check("split " + c[0], result[0] == c[2] && result[1] == c[3])
        }

        expectIllegalArgument("rejects pct above 1", 1000.0, 1.5);
        expectIllegalArgument("rejects negative remaining", -1.0, 0.20);
        System.out.println("failures=" + failures);
    }
}