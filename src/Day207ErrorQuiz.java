public class Day207ErrorQuiz {

    private static double dpi(double distributions, double committed) {
        return distributions / committed;
    }

    private static double rvpi(double nav, double paidIn) {
        if (paidIn <= 0) {
            throw new IllegalArgumentException("no paid-in capital yet");
        }
        return nav / paidIn;
    }

    private static double tvpi(double distributions, double nav, double paidIn) {
        return dpi(distributions, paidIn) + nav
    }

    public static void main(String[] args) {
        double paidIn = 4000000.0 + 3000000.0 + 1000000.0;
        double distributions = 500000.0 + 1500000.0;
        double nav = 7200000.0;
        double committed = 10000000.0;
        System.out.printf("dpi %.4f%n", dpi(distributions, committed));
        System.out.printf("rvpi %.4f%n", rvpi(nav, paidIn));
        System.out.printf("tvpi %.2f%n", tvpi(distributions, nav, paidIn));
    }
}