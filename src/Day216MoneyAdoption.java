import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Adopting a Money type across a toolkit is a decision about boundaries,
 * not a search and replace. A double arriving from JSON is converted once,
 * at the edge, with BigDecimal.valueOf (or new BigDecimal(String)) - never
 * new BigDecimal(double), which faithfully preserves the binary error it
 * was meant to remove. Inside, amounts are Money and ratios stay plain
 * BigDecimal: Money times a ratio is Money, Money over Money is a ratio.
 * Dividing also needs an explicit scale and rounding, because a
 * non-terminating quotient throws ArithmeticException.
 */
public class Day216MoneyAdoption {

    private static final String GBP = "GBP";

    private static final class Money {
        private final BigDecimal amount;
        private final String currency;

        private Money(BigDecimal amount, String currency) {
            this.amount = amount.setScale(2, RoundingMode.HALF_UP);
            this.currency = currency;
        }

        static Money of(String amount, String currency) {
            return new Money(new BigDecimal(amount), currency);
        }

        static Money fromDouble(double amount, String currency) {
            return new Money(BigDecimal.valueOf(amount), currency);
        }

        Money add(Money other) {
            requireSameCurrency(other);
            return new Money(this.amount.add(other.amount), this.currency);
        }

        Money times(BigDecimal ratio) {
            return new Money(this.amount.multiply(ratio), this.currency);
        }

        BigDecimal over(Money other) {
            requireSameCurrency(other);
            if (other.amount.signum() == 0) {
                throw new IllegalArgumentException("cannot divide by a zero amount");
            }
            return this.amount.divide(other.amount, 4, RoundingMode.HALF_UP);
        }

        private void requireSameCurrency(Money other) {
            if (!this.currency.equals(other.currency)) {
                throw new IllegalArgumentException(
                        "cannot combine " + this.currency + " and " + other.currency);
            }
        }

        @Override
        public String toString() {
            return this.currency + " " + this.amount;
        }
    }

    private static BigDecimal toRatio(double value) {
        return BigDecimal.valueOf(value);
    }

    private static Money total(List<Double> amounts, String currency) {
        Money running = Money.of("0", currency);
        for (double amount : amounts) {
            running = running.add(Money.fromDouble(amount, currency));
        }
        return running;
    }

    public static void main(String[] args) {
        double costsPct = 0.068;
        System.out.println("naive new BigDecimal(double): " + new BigDecimal(costsPct));
        System.out.println("boundary conversion:          " + toRatio(costsPct));

        Money price = Money.fromDouble(8_000_000.0, GBP);
        Money acquisition = price.add(price.times(toRatio(costsPct)));
        Money rent = total(List.of(250_000.0, 180_000.0), GBP);

        System.out.println("price " + price + ", acquisition " + acquisition);
        System.out.println("passing rent " + rent);
        System.out.println("net initial yield " + rent.over(acquisition));

        try {
            rent.over(Money.of("0", GBP));
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            Money.of("10", GBP).add(Money.of("10", "EUR"));
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}