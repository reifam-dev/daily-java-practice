import java.util.HashMap;

public class Day54Functools {

    public static void main(String[] args) {

        System.out.println("=== Memoised Fibonacci ===\n");
        MemoFibonacci fib = new MemoFibonacci();
        for (int n : new int[]{10, 20, 30, 10, 20}) {
            System.out.printf("  fibonacci(%d) = %d%n", n, fib.compute(n));
        }
        System.out.println("  Cache size: " + fib.getCacheSize());

        System.out.println("\n=== Partial-style Adder ===\n");
        Adder addTen = new Adder(10);
        Adder addHundred = new Adder(100);
        System.out.println("  addTen(5)     = " + addTen.apply(5));
        System.out.println("  addTen(15)    = " + addTen.apply(15));
        System.out.println("  addHundred(7) = " + addHundred.apply(7));

        System.out.println("\n=== Reduce-style operations ===\n");
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("  Sum     : " + sum(numbers));
        System.out.println("  Product : " + product(numbers));
        System.out.println("  Maximum : " + maximum(numbers));

    }

    public static int sum(int[] numbers) {
        int total = 0;
        for (int n : numbers) total += n;
        return total;
    }

    public static int product(int[] numbers) {
        int result = 1;
        for (int n : numbers) result *= n;
        return result;
    }

    public static int maximum(int[] numbers) {
        int max = numbers[0];
        for (int n : numbers) {
            if (n > max) max = n;
        }
        return max;
    }

}

class MemoFibonacci {

    private HashMap<Integer, Long> cache = new HashMap<>();

    public long compute(int n) {
        if (n <= 1) return n;
        if (cache.containsKey(n)) return cache.get(n);
        long result = compute(n - 1) + compute(n - 2);
        cache.put(n, result);
        return result;
    }

    public int getCacheSize() {
        return cache.size();
    }

}

class Adder {

    private int operand;

    public Adder(int operand) {
        this.operand = operand;
    }

    public int apply(int value) {
        return value + operand;
    }

    @Override
    public String toString() {
        return "Adder(+" + operand + ")";
    }

}