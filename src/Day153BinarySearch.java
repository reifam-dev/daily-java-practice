public class Day153BinarySearch {
    private static int binarySearch(int[] sortedArray, int target) {
        int low = 0;
        int high = sortedArray.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid] == target) {
                return mid;
            } else if (sortedArray[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] deals = {10, 20, 30, 40, 50, 60, 70};
        System.out.println(binarySearch(deals, 40));
        System.out.println(binarySearch(deals, 25));
    }
}