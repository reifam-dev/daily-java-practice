/**
 * WALE: Weighted Average Lease Expiry. Each lease's remaining term is
 * weighted by its ANNUAL RENT, so the result is divided by TOTAL RENT
 * (not the number of leases). A lease past expiry has no remaining
 * term, so it counts as 0 years rather than a negative number.
 */
public class Day190Wale {

    private static double wale(double[][] leases) {
        double totalRent = 0.0;
        for (double[] lease : leases) {
            totalRent += lease[0];
        }
        if (totalRent <= 0) {
            throw new IllegalArgumentException("total rent must be positive");
        }
        double weighted = 0.0;
        for (double[] lease : leases) {
            weighted += lease[0] * Math.max(lease[1], 0.0);
        }
        return weighted / totalRent;
    }

    public static void main(String[] args) {
        double[][] leases = {
                {250000.0, 4.0}, {180000.0, 3.0}, {90000.0, 1.0}, {60000.0, -0.5}};
        System.out.printf("%.2f%n", wale(leases));
        try {
            wale(new double[0][]);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}