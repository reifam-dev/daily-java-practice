import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Day216ErrorQuiz {

    private static final String GBP = "GBP";

    private static final class Money {
        private final BigDecimal amount;
        private final String currency;

        private Money(BigDecimal amount, String currency) {
            this.amount = amount.setScale(2, RoundingMode.HALF_UP);
            this.currency = currency
        }

        static Money of(String amount, String currency) {
            return new Money(new BigDecimal(amount), currency);
        }

        static Money fromDouble(double amount, String currency) {
            return new Money(BigDecimal.valueOf(amount), currency);
        }

        Money add(Money other) {
            return new Money(this.amount.add(other.amount), this.currency);
        }

        Money times(BigDecimal ratio) {
            return new Money(this.amount.multiply(ratio), this.currency);
        }

        BigDecimal over(Money other) {
            return this.amount.divide(other.amount);
        }

        @Override
        public String toString() {
            return this.currency + " " + this.amount;
        }
    }

    private static BigDecimal toRatio(double value) {
        return new BigDecimal(value);
    }

    public static void main(String[] args) {
        double costsPct = 0.068;
        System.out.println("costs ratio: " + toRatio(costsPct));

        Money price = Money.fromDouble(8_000_000.0, GBP);
        Money acquisition = price.add(price.times(BigDecimal.valueOf(costsPct)));
        Money rent = Money.of("0", GBP);
        for (double amount : List.of(250_000.0, 180_000.0)) {
            rent = rent.add(Money.fromDouble(amount, GBP));
        }
        System.out.println("price " + price + ", acquisition " + acquisition);
        System.out.println("passing rent " + rent);

        try {
            System.out.println("net initial yield " + rent.over(acquisition));
        } catch (ArithmeticException e) {
            System.out.println("Crashed: " + e.getMessage());
        }
    }
}