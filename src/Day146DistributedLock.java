import java.util.HashMap;
import java.util.Map;

final class LockNotHeldException extends RuntimeException {
    public LockNotHeldException(String message) {
        super(message);
    }
}

final class LockEntry {
    private final String holderId;
    private final long expiresAtMillis;

    public LockEntry(String holderId, long expiresAtMillis) {
        this.holderId = holderId;
        this.expiresAtMillis = expiresAtMillis;
    }

    public String getHolderId() {
        return this.holderId;
    }

    public long getExpiresAtMillis() {
        return this.expiresAtMillis;
    }
}

/**
 * A lock with both a TTL (so a crashed holder can't lock a resource
 * forever) and ownership tracking (so only the process that acquired
 * a lock can release it).
 */
public class Day146DistributedLock {

    private static final Map<String, LockEntry> locks = new HashMap<>();

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
        if (!locks.containsKey(resourceId)) {
            throw new LockNotHeldException("No lock held on " + resourceId);
        }

        LockEntry entry = locks.get(resourceId);
        if (!entry.getHolderId().equals(holderId)) {
            throw new LockNotHeldException(
                    holderId + " does not hold the lock on " + resourceId
                            + " (held by " + entry.getHolderId() + ")");
        }

        locks.remove(resourceId);
        return true;
    }

    private static String processDealRevaluation(String dealId, String workerId) {
        if (!acquireLock(dealId, workerId, 5000)) {
            return workerId + ": skipped, " + dealId + " is locked";
        }

        try {
            return workerId + ": revalued " + dealId;
        } finally {
            releaseLock(dealId, workerId);
        }
    }

    public static void main(String[] args) {
        System.out.println(processDealRevaluation("deal-1", "worker-a"));
        System.out.println(processDealRevaluation("deal-1", "worker-b"));

        acquireLock("deal-2", "worker-a", 5000);
        try {
            releaseLock("deal-2", "worker-b");
        } catch (LockNotHeldException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}