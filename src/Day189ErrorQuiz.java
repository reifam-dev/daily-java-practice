public class Day189ErrorQuiz {

    private static double dscr(double noi, double interest, double principal) {
        return noi / interest;
    }

    private static double ltv(double loan, double value) {
        return loan / value;
    }

    private static boolean isBreach(double actual, double minimum) {
        return actual <= minimum
    }

    public static void main(String[] args) {
        System.out.printf("%.4f%n", dscr(1300000.0, 600000.0, 440000.0));
        System.out.printf("%.4f%n", ltv(12000000.0, 18000000.0));
        System.out.println(isBreach(1.25, 1.25));
    }
}