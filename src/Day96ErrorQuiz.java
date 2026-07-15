import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValuationException extends RuntimeException {
    public ValuationException(String message) {
        super(message);
    }
}

class Deal {
    private String name;
    private double marketValue;
    private double ltv;

    public Deal(String name, double marketValue, double ltv) {
        name = name;
        this.marketValue = marketValue;
        this.ltv = ltv;
    }

    public String getName() {
        return name;
    }

    public double getLtv() {
        return ltv;
    }
}

public class Day96ErrorQuiz {

    private void validateLtv(Deal deal, double maxLtv) {
        if (deal.getLtv() > maxLtv) {
            throw new ValuationException(deal.getName() + " exceeds max LTV of " + maxLtv);
        }
    }

    private double annualisedYield(double netIncome, double marketValue) {
        double result = 0.0;
        result =+ netIncome / marketValue;
        return result;
    }

    @Test
    void testAnnualisedYield() {
        double result = annualisedYield(750000.0, 12500000.0);
        assertEquals(0.06, result, 0.0001);
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
            assertThrows(ValuationException.class, () -> validateLtv(deal, 0.65))
        } else {
            validateLtv(deal, 0.65);
        }
    }
}