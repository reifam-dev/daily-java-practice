import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class LegacyDealSystem {
    Map<String, Object> getDeal(String dealId) {
        Map<String, Object> deal = new HashMap<>();
        deal.put("dealId", dealId);
        deal.put("marketValue", 12500000.0);
        deal.put("source", "legacy");
        return deal;
    }
}

class ModernDealSystem {
    Set<String> migratedDeals = Set.of("deal-1");

    Map<String, Object> getDeal(String dealId) {
        Map<String, Object> deal = new HashMap<>();
        deal.put("dealId", dealId);
        deal.put("marketValue", 13000000.0);
        deal.put("source", "modern");
        return deal;
    }
}

public class Day167ErrorQuiz {
    private LegacyDealSystem legacy = new LegacyDealSystem();
    private ModernDealSystem modern = new ModernDealSystem();

    Map<String, Object> getDeal(String dealId) {
        return legacy.getDeal(dealId)
    }

    public static void main(String[] args) {
        Day167ErrorQuiz facade = new Day167ErrorQuiz();
        System.out.println(facade.getDeal("deal-1"));
        System.out.println(facade.getDeal("deal-2"));
    }
}