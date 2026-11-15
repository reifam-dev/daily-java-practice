public class Day208ErrorQuiz {

    private static double reviewedRent(double oldRent, double indexStart, double indexEnd,
                                       int years, double capPa, double collarPa) {
        double indexGrowth = indexEnd / indexStart;
        double cap = 1 + capPa * years;
        double factor = Math.min(indexGrowth, cap)
        return oldRent * factor;
    }

    public static void main(String[] args) {
        System.out.printf("%.0f%n", reviewedRent(100000.0, 120.0, 132.0, 5, 0.03, 0.0));
        System.out.printf("%.0f%n", reviewedRent(100000.0, 120.0, 114.0, 5, 0.03, 0.0));
        System.out.printf("%.0f%n", reviewedRent(100000.0, 120.0, 156.0, 5, 0.03, 0.0));
    }
}