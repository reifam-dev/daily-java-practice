import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * Money with BigDecimal, never double: values are built from strings,
 * rounded with an explicit mode, compared with compareTo (equals also
 * compares scale, so 2.0 is not equals to 2.00), and splits allocate
 * leftover pennies so shares always sum back to the original.
 */
public class Day177MoneyArithmetic {

    private static final BigDecimal CENT = new BigDecimal("0.01");

    private static BigDecimal applyRate(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }

    private static boolean sameAmount(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) == 0;
    }

    private static BigDecimal[] splitEvenly(BigDecimal amount, int parts) {
        if (parts <= 0) {
            throw new IllegalArgumentException("parts must be positive");
        }
        BigDecimal base = amount.divide(BigDecimal.valueOf(parts), 2, RoundingMode.DOWN);
        BigDecimal leftover = amount.subtract(base.multiply(BigDecimal.valueOf(parts)));
        int leftoverPennies = leftover.divide(CENT).intValueExact();

        BigDecimal[] shares = new BigDecimal[parts];
        for (int i = 0; i < parts; i++) {
            shares[i] = i < leftoverPennies ? base.add(CENT) : base;
        }
        return shares;
    }

    public static void main(String[] args) {
        System.out.println(applyRate(new BigDecimal("0.10"), new BigDecimal("3")));
        System.out.println(sameAmount(new BigDecimal("2.0"), new BigDecimal("2.00")));

        BigDecimal[] shares = splitEvenly(new BigDecimal("100.00"), 3);
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal share : shares) {
            sum = sum.add(share);
        }
        System.out.println(Arrays.toString(shares) + " sum=" + sum);
    }
}