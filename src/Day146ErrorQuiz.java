import java.util.HashMap;
import java.util.Map;

class LockEntry {
    private String holderId;
    private long expiresAtMillis;

    public LockEntry(String holderId, long expiresAtMillis) {
        this.holderId = holderId;
        this.expiresAtMillis = expiresAtMillis;
    }

    public String getHolderId() {
        return holderId;
    }

    public long getExpiresAtMillis() {
        return expiresAtMillis;
    }
}

public class Day146ErrorQuiz {

    private static Map<String, LockEntry> locks = new HashMap<>();

    private static boolean acquireLock(String resourceId, String holderId, long ttlMillis) {
        long now = System.currentTimeMillis();

        if (locks.containsKey(resourceId)) {
            LockEntry entry = locks.get(resourceId);
            if (now < entry.getExpiresAtMillis()) {
                return false;
            }
        }

        locks.put(resourceId, new LockEntry(holderId, now + ttlMillis));
        return true;
    }

    private static boolean releaseLock(String resourceId, String holderId) {
        locks.remove(resourceId)
        return true;
    }

    private static String processDealRevaluation(String dealId, String workerId) {
        if (!acquireLock(dealId, workerId, 5000)) {
            return workerId + ": skipped, " + dealId + " is locked";
        }

        String result = workerId + ": revalued " + dealId;
        releaseLock(dealId, workerId);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(processDealRevaluation("deal-1", "worker-a"));
        System.out.println(processDealRevaluation("deal-1", "worker-b"));
    }
}