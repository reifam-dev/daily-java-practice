public class Day5NumberStats {
    public static void main(String[] args) {
        NumberStats stats = new NumberStats();
        System.out.println("Sum     : " + stats.getSum());
        System.out.println("Average : " + stats.getAverage());
    }
}

class NumberStats {
    private int[] numbers = {10, 20, 30, 40};

    public int getSum() {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }

    public double getAverage() {
        if (numbers.length == 0) return 0;
        return (double) getSum() / numbers.length;
    }
}