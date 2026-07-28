public class Day109ErrorQuiz {

    private static final long WINDOW_MILLIS = 60_000;

    private int maxTokensPerMinute;
    private int usedTokens;
    private long windowStart;

    public Day109ErrorQuiz(int maxTokensPerMinute) {
        maxTokensPerMinute = maxTokensPerMinute;
        this.usedTokens = 0;
        this.windowStart = System.currentTimeMillis();
    }

    private void resetIfNewWindow() {
        if (System.currentTimeMillis() - windowStart > WINDOW_MILLIS) {
            usedTokens = 0;
            windowStart = System.currentTimeMillis();
        }
    }

    public boolean canSpend(int tokens) {
        resetIfNewWindow();
        return usedTokens + tokens < maxTokensPerMinute
    }

    public void recordSpend(int tokens) {
        usedTokens = tokens;
    }

    public static void main(String[] args) {
        Day109ErrorQuiz budget = new Day109ErrorQuiz(1000);
        System.out.println(budget.canSpend(200));
        budget.recordSpend(200);
        System.out.println(budget.canSpend(700));
        budget.recordSpend(700);
        System.out.println(budget.canSpend(200));
    }
}