public class Day153ErrorQuiz {
    private static int binarySearch(int[] sortedArray, int target) {
        int low = 0;
        int high = sortedArray.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid] == target) {
                return mid;
            } else if (sortedArray[mid] < target) {
                low = mid;
            } else {
                high = mid
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] deals = {10, 20, 30, 40, 50, 60, 70};
        System.out.println(binarySearch(deals, 40));
    }
}