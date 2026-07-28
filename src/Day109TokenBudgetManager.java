/**
 * Tracks cumulative token spend within a rolling one-minute window,
 * refusing new requests once the budget would be exceeded - a Java
 * analogue of the Python TokenBudget class.
 */
public class Day109TokenBudgetManager {

    private static final long WINDOW_MILLIS = 60_000;

    private final int maxTokensPerMinute;
    private int usedTokens;
    private long windowStart;

    public Day109TokenBudgetManager(int maxTokensPerMinute) {
        this.maxTokensPerMinute = maxTokensPerMinute;
        this.usedTokens = 0;
        this.windowStart = System.currentTimeMillis();
    }

    private void resetIfNewWindow() {
        if (System.currentTimeMillis() - this.windowStart > WINDOW_MILLIS) {
            this.usedTokens = 0;
            this.windowStart = System.currentTimeMillis();
        }
    }

    public boolean canSpend(int tokens) {
        resetIfNewWindow();
        return this.usedTokens + tokens <= this.maxTokensPerMinute;
    }

    public void recordSpend(int tokens) {
        resetIfNewWindow();
        this.usedTokens += tokens;
    }

    public int remaining() {
        resetIfNewWindow();
        return this.maxTokensPerMinute - this.usedTokens;
    }

    public static void main(String[] args) {
        Day109TokenBudgetManager budget = new Day109TokenBudgetManager(1000);
        System.out.println(budget.canSpend(200));
        budget.recordSpend(200);
        System.out.println(budget.canSpend(700));
        budget.recordSpend(700);
        System.out.println(budget.canSpend(200));
        System.out.println("Remaining: " + budget.remaining());
    }
}