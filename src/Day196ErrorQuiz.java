import java.util.Arrays;

public class Day196ErrorQuiz {

    private static double percentile(double[] values, double p) {
        Arrays.sort(values);
        int index = (int) (values.length * p / 100);
        return values[index]
    }

    public static void main(String[] args) {
        double[] latenciesMs = {120, 95, 180, 110, 3000, 105, 130, 99, 101, 115};
        System.out.println(percentile(latenciesMs, 50));
        System.out.println(Arrays.toString(latenciesMs));
        System.out.println(percentile(latenciesMs, 95));
        System.out.println(percentile(latenciesMs, 100));
    }
}