public class Day13GradeCalculator {

    public static void main(String[] args) {

        GradeCalculator calc = new GradeCalculator();
        System.out.printf("Average : %.1f%n", calc.getAverage());
        System.out.println("Passing : " + calc.isPassing());
        System.out.println("Highest : " + calc.getHighest());

    }

}

class GradeCalculator {

    private double[] grades = {45, 72, 88, 60, 55};

    public double getAverage() {
        double total = 0;
        for (double grade : grades) {
            total += grade;
        }
        return total / grades.length;
    }

    public boolean isPassing() {
        return getAverage() >= 50;
    }

    public double getHighest() {
        double highest = grades[0];
        for (double grade : grades) {
            if (grade > highest) highest = grade;
        }
        return highest;
    }

}