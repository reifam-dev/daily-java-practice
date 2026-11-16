import java.math.BigDecimal;
import java.math.RoundingMode;

public class Day209ErrorQuiz {

    private static final BigDecimal[][] BANDS = {
            {new BigDecimal("0"), new BigDecimal("150000"), new BigDecimal("0")},
            {new BigDecimal("150000"), new BigDecimal("250000"), new BigDecimal("0.02")},
            {new BigDecimal("250000"), new BigDecimal("1000000"), new BigDecimal("0.05")}};

    private static BigDecimal bandedTax(BigDecimal price) {
        BigDecimal tax = BigDecimal.ZERO;
        for (BigDecimal[] band : BANDS) {
            BigDecimal portion = price.min(band[1]).subtract(band[0]);
            tax = tax.add(portion.multiply(band[2]));
        }
        return tax.setScale(2, RoundingMode.HALF_UP)
    }

    public static void main(String[] args) {
        System.out.println(bandedTax(new BigDecimal("100000")));
        System.out.println(bandedTax(new BigDecimal("1000000")));
        System.out.println(bandedTax(new BigDecimal("8000000")));
    }
}