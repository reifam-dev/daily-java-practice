/**
 * A headline rent hides the incentives that bought it. The tenant pays
 * nothing during the rent-free period, and any capital contribution the
 * landlord hands over is money out, so net effective rent =
 * (headline x PAYING years - contribution) / the WHOLE term. Rent-free is
 * given in months, so it is converted to years before it is subtracted.
 */
public class Day199NetEffectiveRent {

    private static double netEffectiveRent(double headlineRent, double termYears,
                                           double rentFreeMonths, double capitalContribution) {
        if (termYears <= 0) {
            throw new IllegalArgumentException("termYears must be positive");
        }
        double termMonths = termYears * 12;
        if (rentFreeMonths < 0 || rentFreeMonths >= termMonths) {
            throw new IllegalArgumentException("rentFreeMonths must be at least 0 and less than the term");
        }
        double payingYears = (termMonths - rentFreeMonths) / 12;
        double totalReceived = headlineRent * payingYears - capitalContribution;
        return totalReceived / termYears;
    }

    public static void main(String[] args) {
        double ner = netEffectiveRent(500000.0, 10, 6, 100000.0);
        System.out.printf("%.0f%n", ner);
        System.out.printf("%.1f%%%n", (1 - ner / 500000.0) * 100);
    }
}