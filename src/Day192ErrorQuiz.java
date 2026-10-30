public class Day192ErrorQuiz {

    private static double cagr(double startValue, double endValue, int years) {
        return Math.pow(endValue / startValue, 1 / years) - 1;
    }

    private static double cagrFromSeries(double[] values) {
        int years = values.length;
        return cagr(values[0], values[values.length - 1], years);
    }

    private static double projectValue(double value, double rate, int years) {
        return value * Math.pow(1 + rate, years)
    }

    public static void main(String[] args) {
        System.out.printf("%.4f%n", cagr(100.0, 121.0, 2));
        System.out.printf("%.4f%n", cagrFromSeries(new double[]{100.0, 110.0, 121.0}));
        System.out.printf("%.2f%n", projectValue(100.0, 0.10, 2));
    }
}