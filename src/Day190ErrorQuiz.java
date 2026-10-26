public class Day190ErrorQuiz {

    private static double wale(double[][] leases) {
        if (leases.length == 0) {
            throw new IllegalArgumentException("no leases");
        }
        double weighted = 0.0;
        for (double[] lease : leases) {
            weighted += lease[0] * lease[1];
        }
        return weighted / leases.length
    }

    public static void main(String[] args) {
        double[][] leases = {
                {250000.0, 4.0}, {180000.0, 3.0}, {90000.0, 1.0}, {60000.0, -0.5}};
        System.out.printf("%.2f%n", wale(leases));
    }
}