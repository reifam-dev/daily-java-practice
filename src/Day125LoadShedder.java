import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Rejects incoming requests once at capacity, except critical-priority
 * requests, which are always accepted - protecting the most important
 * work rather than treating all requests equally under load.
 */
public class Day125LoadShedder {

    private static final int MAX_CONCURRENT = 3;
    private static final Map<String, Integer> PRIORITY_ORDER = Map.of(
            "critical", 0, "normal", 1, "low", 2);

    private int activeRequests;

    public boolean accept(String priority) {
        if (this.activeRequests >= MAX_CONCURRENT) {
            if (priority.equals("critical")) {
                this.activeRequests++;
                return true;
            }
            return false;
        }

        this.activeRequests++;
        return true;
    }

    public void release() {
        if (this.activeRequests > 0) {
            this.activeRequests--;
        }
    }

    public static void main(String[] args) {
        Day125LoadShedder shedder = new Day125LoadShedder();

        List<String> priorities = Arrays.asList("normal", "critical", "normal", "low", "critical");
        priorities.sort(Comparator.comparingInt(PRIORITY_ORDER::get));

        int accepted = 0;
        int shed = 0;

        for (String priority : priorities) {
            if (shedder.accept(priority)) {
                accepted++;
            } else {
                shed++;
            }
        }

        System.out.println("accepted=" + accepted + ", shed=" + shed);
    }
}