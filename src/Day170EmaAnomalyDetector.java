public class Day170EmaAnomalyDetector {
    private final double alpha;
    private final double thresholdPct;
    private Double ema;

    public Day170EmaAnomalyDetector(double alpha, double thresholdPct) {
        if (alpha <= 0.0 || alpha > 1.0) {
            throw new IllegalArgumentException("alpha must be in (0.0, 1.0]");
        }
        this.alpha = alpha;
        this.thresholdPct = thresholdPct;
        this.ema = null;
    }

    public boolean check(double value) {
        if (this.ema == null) {
            this.ema = value;
            return false;
        }

        double deviation = Math.abs(value - this.ema) / this.ema;
        boolean isAnomaly = deviation > this.thresholdPct;

        this.ema = this.alpha * value + (1 - this.alpha) * this.ema;

        return isAnomaly;
    }

    public static void main(String[] args) {
        Day170EmaAnomalyDetector detector = new Day170EmaAnomalyDetector(0.3, 0.5);
        double[] values = {100, 102, 98, 101, 500, 99, 103};
        for (double v : values) {
            System.out.println(v + ": anomaly=" + detector.check(v));
        }
    }
}