import java.util.Arrays;

public class Day44StrategyPattern {

    public static void main(String[] args) {

        int[] data = {5, 3, 8, 1, 9, 2, 7, 4, 6};

        Sorter sorter = new Sorter(new BubbleSortStrategy());
        System.out.println("Strategy  : " + sorter.getStrategyName());
        System.out.println("Sorted    : " + Arrays.toString(sorter.sort(data)));

        sorter.setStrategy(new SelectionSortStrategy());
        System.out.println("\nStrategy  : " + sorter.getStrategyName());
        System.out.println("Sorted    : " + Arrays.toString(sorter.sort(data)));

        System.out.println("\nOriginal  : " + Arrays.toString(data));

    }

}

interface SortStrategy {
    int[] sort(int[] data);
    String getName();
}

class BubbleSortStrategy implements SortStrategy {

    public String getName() { return "BubbleSort"; }

    public int[] sort(int[] data) {
        int[] arr = Arrays.copyOf(data, data.length);
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

}

class SelectionSortStrategy implements SortStrategy {

    public String getName() { return "SelectionSort"; }

    public int[] sort(int[] data) {
        int[] arr = Arrays.copyOf(data, data.length);
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
        return arr;
    }

}

class Sorter {

    private SortStrategy strategy;

    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public String getStrategyName() {
        return strategy.getName();
    }

    public int[] sort(int[] data) {
        return strategy.sort(data);
    }

}