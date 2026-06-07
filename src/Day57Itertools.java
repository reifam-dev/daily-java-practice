import java.util.ArrayList;

public class Day57Itertools {

    public static void main(String[] args) {

        System.out.println("=== Cartesian product ===\n");
        String[] colours = {"red", "blue"};
        String[] sizes = {"S", "M", "L"};
        ArrayList<String> variants = new ArrayList<>();
        for (String colour : colours) {
            for (String size : sizes) {
                variants.add(colour + "-" + size);
            }
        }
        System.out.println("  Variants: " + variants);

        System.out.println("\n=== Running sum (accumulate) ===\n");
        int[] numbers = {1, 2, 3, 4, 5};
        int total = 0;
        ArrayList<Integer> running = new ArrayList<>();
        for (int n : numbers) {
            total += n;
            running.add(total);
        }
        System.out.println("  Running sum: " + running);

        System.out.println("\n=== Chain (combine lists) ===\n");
        String[] list1 = {"a", "b", "c"};
        String[] list2 = {"d", "e", "f"};
        ArrayList<String> combined = new ArrayList<>();
        for (String s : list1) combined.add(s);
        for (String s : list2) combined.add(s);
        System.out.println("  Combined: " + combined);

        System.out.println("\n=== Combinations (choose 2 from 4) ===\n");
        String[] items = {"A", "B", "C", "D"};
        int count = 0;
        for (int i = 0; i < items.length; i++) {
            for (int j = i + 1; j < items.length; j++) {
                System.out.println("  (" + items[i] + ", " + items[j] + ")");
                count++;
            }
        }
        System.out.println("  Total: " + count);

    }

}