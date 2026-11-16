import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Marginal bands, not a flat slab: each band's rate applies ONLY to the
 * slice of the price inside that band, portion = max(0, min(price, upper)
 * - lower), and the last band is open-ended (null upper). The bands are the
 * England and Northern Ireland non-residential SDLT bands for 2026/27
 * (0% to 150,000, 2% to 250,000, 5% above); Scotland and Wales differ, so
 * check current official rates. A coding exercise, not tax advice.
 */
public class Day209BandedTransactionTax {

    private static final BigDecimal[][] BANDS = {
            {new BigDecimal("0"), new BigDecimal("150000"), new BigDecimal("0")},
            {new BigDecimal("150000"), new BigDecimal("250000"), new BigDecimal("0.02")},
            {new BigDecimal("250000"), null, new BigDecimal("0.05")}};

    private static BigDecimal bandedTax(BigDecimal price) {
        if (price.signum() < 0) {
            throw new IllegalArgumentException("price must not be negative");
        }
        BigDecimal tax = BigDecimal.ZERO;
        for (BigDecimal[] band : BANDS) {
            BigDecimal lower = band[0];
            BigDecimal upper = band[1];
            BigDecimal rate = band[2];
            BigDecimal top = (upper == null) ? price : price.min(upper);
            BigDecimal portion = top.subtract(lower).max(BigDecimal.ZERO);
            tax = tax.add(portion.multiply(rate));
        }
        return tax.setScale(2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {
        System.out.println(bandedTax(new BigDecimal("100000")));
        System.out.println(bandedTax(new BigDecimal("1000000")));
        System.out.println(bandedTax(new BigDecimal("8000000")));
    }
}