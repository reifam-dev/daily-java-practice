public class Day8NumberProcessor {
    public static void main(String[] args) {
        NumberProcessor processor = new NumberProcessor();
        System.out.println("Max  : " + processor.getMax());
        System.out.println("Min  : " + processor.getMin());
        System.out.println("Sum  : " + processor.getSum());
    }
}

class NumberProcessor {
    private int[] numbers = {10, 5, 8, 12, 3};

    public int getMax() {
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) max = num;
        }
        return max;
    }

    public int getMin() {
        int min = numbers[0];
        for (int num : numbers) {
            if (num < min) min = num;
        }
        return min;
    }

    public int getSum() {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }
}