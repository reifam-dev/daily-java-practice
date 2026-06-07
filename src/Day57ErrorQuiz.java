// Day 57 - Error Finding Quiz
// Find and fix the bugs

import java.util.ArrayList;

public class Day57ErrorQuiz {

    public static void main(String[] args) {

        String[] colours = {"red", "blue"};
        String[] sizes = {"S", "M", "L"};
        ArrayList<String> variants = new ArrayList<>();

        for (String colour : colours) {
            for (String size : sizes) {
                variants.add(colour + "-" + size)   // Bug 1 - missing semicolon
            }
        }
        System.out.println("Variants: " + variants.size());

        int[] numbers = {1, 2, 3, 4, 5};
        int runningTotal = 0;
        ArrayList<Integer> running = new ArrayList<>();
        for (int n : numbers) {
            runningTotal =+ n;                       // Bug 2 - wrong operator
            running.add(runningTotal);
        }
        System.out.println("Running sum: " + running);

        ArrayList<String> combined = new ArrayList<>();
        String[] list1 = {"a", "b", "c"};
        String[] list2 = {"d", "e", "f"};
        for (String s : list1) combined.add(s);
        for (String s : list2) combined.add(s)       // Bug 3 - missing semicolon
        System.out.println("Combined: " + combined);

    }

}