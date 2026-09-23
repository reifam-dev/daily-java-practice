class LegacyValuationSystem {
    long getValueInPence(String dealId) {
        return 1_250_000_000L;
    }
}

interface ModernValuationApi {
    double getMarketValue(String dealId);
}

class LegacyValuationAdapter implements ModernValuationApi {
    private LegacyValuationSystem legacySystem;

    LegacyValuationAdapter(LegacyValuationSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    public double getMarketValue(String dealId) {
        long pence = legacySystem.getValueInPence(dealId);
        return pence
    }
}

public class Day164ErrorQuiz {
    public static void main(String[] args) {
        LegacyValuationSystem legacy = new LegacyValuationSystem();
        ModernValuationApi adapter = new LegacyValuationAdapter(legacy);
        System.out.printf("deal-1: £%,.2f%n", adapter.getMarketValue("deal-1"));
    }
}