public class Day199ErrorQuiz {

    private static double netEffectiveRent(double headlineRent, double termYears,
                                           double rentFreeMonths, double capitalContribution) {
        double payingYears = termYears - rentFreeMonths;
        double totalRent = headlineRent * payingYears + capitalContribution;
        return totalRent / termYears
    }

    public static void main(String[] args) {
        System.out.printf("%.0f%n", netEffectiveRent(500000.0, 10, 6, 100000.0));
    }
}