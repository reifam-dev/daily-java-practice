/**
 * Value = NOI / cap rate. The NET initial yield divides NOI by the
 * total cost of acquiring the asset: the price PLUS purchaser's costs
 * (roughly 6.8% is a common UK assumption). Working backwards from a
 * target yield, the headline price is the total cost divided back down
 * by (1 + costs).
 */
public class Day187CapRateValuation {

    private static double valueFromCapRate(double noi, double capRate) {
        if (capRate <= 0) {
            throw new IllegalArgumentException("capRate must be positive");
        }
        return noi / capRate;
    }

    private static double netInitialYield(double noi, double price, double costsPct) {
        if (price <= 0) {
            throw new IllegalArgumentException("price must be positive");
        }
        return noi / (price * (1 + costsPct));
    }

    private static double purchasePriceForYield(double noi, double targetNiy, double costsPct) {
        if (targetNiy <= 0) {
            throw new IllegalArgumentException("targetNiy must be positive");
        }
        return noi / (targetNiy * (1 + costsPct));
    }

    public static void main(String[] args) {
        System.out.printf("%.0f%n", valueFromCapRate(1000000.0, 0.055));
        System.out.printf("%.4f%n", netInitialYield(1000000.0, 18000000.0, 0.068));
        double price = purchasePriceForYield(1000000.0, 0.052, 0.068);
        System.out.printf("%.0f%n", price);
        System.out.printf("%.4f%n", netInitialYield(1000000.0, price, 0.068));
        try {
            valueFromCapRate(1000000.0, 0.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}