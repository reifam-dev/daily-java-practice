/**
 * At review the rent follows an index, limited by a CAP (the most it may
 * rise) and a COLLAR (the least it must rise; a collar of zero makes the
 * review upward-only). Index growth is the RATIO end/start, and cap and
 * collar are quoted per year, so over the review period they COMPOUND:
 * Math.pow(1 + rate, years).
 */
public class Day208RentReviewCapCollar {

    private static double reviewedRent(double oldRent, double indexStart, double indexEnd,
                                       int years, double capPa, double collarPa) {
        if (oldRent <= 0 || indexStart <= 0 || indexEnd <= 0) {
            throw new IllegalArgumentException("rent and index values must be positive");
        }
        if (years <= 0) {
            throw new IllegalArgumentException("years must be positive");
        }
        if (collarPa > capPa) {
            throw new IllegalArgumentException("collar cannot exceed cap");
        }
        double indexGrowth = indexEnd / indexStart;
        double cap = Math.pow(1 + capPa, years);
        double collar = Math.pow(1 + collarPa, years);
        double factor = Math.min(Math.max(indexGrowth, collar), cap);
        return oldRent * factor;
    }

    public static void main(String[] args) {
        System.out.printf("%.0f%n", reviewedRent(100000.0, 120.0, 132.0, 5, 0.03, 0.0));
        System.out.printf("%.0f%n", reviewedRent(100000.0, 120.0, 114.0, 5, 0.03, 0.0));
        System.out.printf("%.0f%n", reviewedRent(100000.0, 120.0, 156.0, 5, 0.03, 0.0));
    }
}