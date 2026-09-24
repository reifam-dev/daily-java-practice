import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class LegacyDealSystem {
    Map<String, Object> getDeal(String dealId) {
        Map<String, Object> deal = new HashMap<>();
        deal.put("dealId", dealId);
        deal.put("marketValue", 12500000.0);
        deal.put("source", "legacy");
        return deal;
    }
}

final class ModernDealSystem {
    final Set<String> migratedDeals = Set.of("deal-1");

    Map<String, Object> getDeal(String dealId) {
        Map<String, Object> deal = new HashMap<>();
        deal.put("dealId", dealId);
        deal.put("marketValue", 13000000.0);
        deal.put("source", "modern");
        return deal;
    }
}

/**
 * Routes each request to either the legacy or modern system based on
 * migration status, allowing gradual migration rather than a
 * risky big-bang cutover.
 */
public class Day167StranglerFacade {
    private final LegacyDealSystem legacy = new LegacyDealSystem();
    private final ModernDealSystem modern = new ModernDealSystem();

    Map<String, Object> getDeal(String dealId) {
        if (this.modern.migratedDeals.contains(dealId)) {
            return this.modern.getDeal(dealId);
        }
        return this.legacy.getDeal(dealId);
    }

    public static void main(String[] args) {
        Day167StranglerFacade facade = new Day167StranglerFacade();
        System.out.println(facade.getDeal("deal-1"));
        System.out.println(facade.getDeal("deal-2"));
    }
}