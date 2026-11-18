import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

/**
 * A bare double cannot tell GBP from EUR and cannot stop 0.1 + 0.2
 * drifting. Money fixes this: the amount is a BigDecimal built from a
 * String and rounded to the penny, adding two currencies throws instead of
 * silently mixing them, the class is immutable, and equals AND hashCode
 * are overridden together so equal values behave in a HashSet.
 */
public class Day210MoneyValueObject {

    private static final class CurrencyMismatchException extends RuntimeException {
        CurrencyMismatchException(String message) {
            super(message);
        }
    }

    private static final class Money {
        private final BigDecimal amount;
        private final String currency;

        Money(String amount, String currency) {
            if (currency == null || currency.length() != 3) {
                throw new IllegalArgumentException("currency must be a 3-letter code");
            }
            this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
            this.currency = currency;
        }

        Money add(Money other) {
            if (!this.currency.equals(other.currency)) {
                throw new CurrencyMismatchException(
                        "cannot combine " + this.currency + " and " + other.currency);
            }
            return new Money(this.amount.add(other.amount).toPlainString(), this.currency);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Money)) {
                return false;
            }
            Money other = (Money) o;
            return this.amount.compareTo(other.amount) == 0 && this.currency.equals(other.currency);
        }

        @Override
        public int hashCode() {
            return 31 * this.amount.hashCode() + this.currency.hashCode();
        }

        @Override
        public String toString() {
            return this.currency + " " + this.amount;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Money("0.10", "GBP").add(new Money("0.20", "GBP")));
        System.out.println(new Money("10", "GBP").equals(new Money("10", "EUR")));

        Set<Money> set = new HashSet<>();
        set.add(new Money("10", "GBP"));
        set.add(new Money("10.00", "GBP"));
        System.out.println(set.size());
        System.out.println(set.contains(new Money("10", "GBP")));

        try {
            new Money("10", "GBP").add(new Money("10", "EUR"));
        } catch (CurrencyMismatchException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}