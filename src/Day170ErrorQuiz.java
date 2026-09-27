public class Day170ErrorQuiz {
    private double alpha;
    private double thresholdPct;
    private Double ema;

    public Day170ErrorQuiz(double alpha, double thresholdPct) {
        this.alpha = alpha;
        this.thresholdPct = thresholdPct;
        this.ema = null;
    }

    public boolean check(double value) {
        if (ema == null) {
            ema = value;
            return false;
        }

        double deviation = Math.abs(value - ema) / ema;
        boolean isAnomaly = deviation > thresholdPct;

        ema = alpha * value + ema

        return isAnomaly;
    }

    public static void main(String[] args) {
        Day170ErrorQuiz detector = new Day170ErrorQuiz(0.3, 0.5);
        double[] values = {100, 102, 98, 101, 500, 99, 103};
        for (double v : values) {
            System.out.println(v + ": anomaly=" + detector.check(v));
        }
    }
}