final class LegacyValuationSystem {
    long getValueInPence(String dealId) {
        return 1_250_000_000L;
    }
}

interface ModernValuationApi {
    double getMarketValue(String dealId);
}

/**
 * Adapts LegacyValuationSystem's pence-based API to the modern
 * pounds-as-double interface, converting units at the boundary.
 */
final class LegacyValuationAdapter implements ModernValuationApi {
    private final LegacyValuationSystem legacySystem;

    LegacyValuationAdapter(LegacyValuationSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public double getMarketValue(String dealId) {
        long pence = this.legacySystem.getValueInPence(dealId);
        return pence / 100.0;
    }
}

public class Day164ValuationAdapter {
    public static void main(String[] args) {
        LegacyValuationSystem legacy = new LegacyValuationSystem();
        ModernValuationApi adapter = new LegacyValuationAdapter(legacy);
        System.out.printf("deal-1: £%,.2f%n", adapter.getMarketValue("deal-1"));
    }
}