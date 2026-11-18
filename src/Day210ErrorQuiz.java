import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Day210ErrorQuiz {

    private static final class Money {
        final BigDecimal amount;
        final String currency;

        Money(String amount, String currency) {
            this.amount = new BigDecimal(amount).setScale(2, java.math.RoundingMode.HALF_UP);
            this.currency = currency;
        }

        Money add(Money other) {
            return new Money(this.amount.add(other.amount).toPlainString(), this.currency)
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Money)) {
                return false;
            }
            Money other = (Money) o;
            return amount.compareTo(other.amount) == 0 && currency.equals(other.currency);
        }

        @Override
        public String toString() {
            return currency + " " + amount;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Money("10", "GBP").add(new Money("10", "EUR")));

        Set<Money> set = new HashSet<>();
        set.add(new Money("10", "GBP"));
        set.add(new Money("10.00", "GBP"));
        System.out.println(set.size());
        System.out.println(set.contains(new Money("10", "GBP")));
    }
}