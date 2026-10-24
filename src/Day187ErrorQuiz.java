public class Day187ErrorQuiz {

    private static double valueFromCapRate(double noi, double capRate) {
        if (capRate <= 0) {
            throw new IllegalArgumentException("capRate must be positive");
        }
        return noi / capRate
    }

    private static double netInitialYield(double noi, double price, double costsPct) {
        return noi / price;
    }

    private static double purchasePriceForYield(double noi, double targetNiy, double costsPct) {
        return noi / targetNiy;
    }

    public static void main(String[] args) {
        System.out.printf("%.0f%n", valueFromCapRate(1000000.0, 0.055));
        System.out.printf("%.4f%n", netInitialYield(1000000.0, 18000000.0, 0.068));
        System.out.printf("%.0f%n", purchasePriceForYield(1000000.0, 0.052, 0.068));
    }
}