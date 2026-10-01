import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Every query filters by tenantId, so one tenant can never see or
 * fetch another tenant's records, even by directly requesting a
 * known dealId belonging to someone else.
 */
public class Day174MultiTenantIsolation {
    private static final List<Map<String, Object>> DEALS = List.of(
            Map.of("tenantId", "tenant-a", "dealId", "deal-1", "marketValue", 12500000.0),
            Map.of("tenantId", "tenant-b", "dealId", "deal-2", "marketValue", 8100000.0),
            Map.of("tenantId", "tenant-a", "dealId", "deal-3", "marketValue", 34200000.0)
    );

    private static List<Map<String, Object>> getDealsForTenant(String tenantId) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> deal : DEALS) {
            if (deal.get("tenantId").equals(tenantId)) {
                result.add(deal);
            }
        }
        return result;
    }

    private static Map<String, Object> getDeal(String tenantId, String dealId) {
        for (Map<String, Object> deal : DEALS) {
            if (deal.get("dealId").equals(dealId) && deal.get("tenantId").equals(tenantId)) {
                return deal;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getDealsForTenant("tenant-a"));
        System.out.println(getDeal("tenant-a", "deal-2"));
    }
}