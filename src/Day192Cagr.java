/**
 * CAGR = (end / start) ^ (1 / years) - 1. The exponent must use
 * floating-point division (1.0 / years; integer 1 / years is 0 for any
 * years above 1), and a series of N values spans N - 1 growth periods.
 */
public class Day192Cagr {

    private static double cagr(double startValue, double endValue, int years) {
        if (startValue <= 0 || endValue <= 0) {
            throw new IllegalArgumentException("start and end values must be positive");
        }
        if (years <= 0) {
            throw new IllegalArgumentException("years must be positive");
        }
        return Math.pow(endValue / startValue, 1.0 / years) - 1;
    }

    private static double cagrFromSeries(double[] values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("need at least two values");
        }
        return cagr(values[0], values[values.length - 1], values.length - 1);
    }

    private static double projectValue(double value, double rate, int years) {
        return value * Math.pow(1 + rate, years);
    }

    public static void main(String[] args) {
        System.out.printf("%.4f%n", cagr(100.0, 121.0, 2));
        System.out.printf("%.4f%n", cagrFromSeries(new double[]{100.0, 110.0, 121.0}));
        System.out.printf("%.2f%n", projectValue(100.0, 0.10, 2));
    }
}