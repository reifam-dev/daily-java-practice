import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class ValuationException extends RuntimeException {
    public ValuationException(String message) {
        super(message);
    }
}

final class Deal {
    private final String name;
    private final double marketValue;
    private final double ltv;

    public Deal(String name, double marketValue, double ltv) {
        this.name = name;
        this.marketValue = marketValue;
        this.ltv = ltv;
    }

    public String getName() {
        return this.name;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    public double getLtv() {
        return this.ltv;
    }

    @Override
    public String toString() {
        return "Deal{name='" + this.name + "', marketValue=" + this.marketValue
                + ", ltv=" + this.ltv + '}';
    }
}

/**
 * JUnit 5 test suite covering LTV validation and annualised yield
 * calculations, including parameterised expected-exception cases.
 */
public class Day96ValuationTestsAdvanced {

    private static final double DELTA = 0.0001;

    private void validateLtv(Deal deal, double maxLtv) {
        if (deal.getLtv() > maxLtv) {
            throw new ValuationException(deal.getName() + " exceeds max LTV of " + maxLtv);
        }
    }

    private double annualisedYield(double netIncome, double marketValue) {
        return netIncome / marketValue;
    }

    @Test
    void testAnnualisedYield() {
        double result = annualisedYield(750000.0, 12500000.0);
        assertEquals(0.06, result, DELTA);
    }

    @ParameterizedTest
    @CsvSource({
            "0.50, false",
            "0.65, false",
            "0.70, true"
    })
    void testValidateLtv(double ltv, boolean shouldRaise) {
        Deal deal = new Deal("Test Deal", 1000000.0, ltv);
        if (shouldRaise) {
            assertThrows(ValuationException.class, () -> validateLtv(deal, 0.65));
        } else {
            validateLtv(deal, 0.65);
        }
    }
}