public class Day11ListManager {
    public static void main(String[] args) {
        ListManager manager = new ListManager();
        manager.addItem("apple");
        manager.addItem("banana");
        System.out.println("Contains apple: " + manager.contains("apple"));
        System.out.println("Size: " + manager.size());
    }
}

class ListManager {
    private String[] items = new String[10];
    private int count = 0;

    public void addItem(String item) {
        if (count < items.length) {
            items[count] = item;
            count++;
        }
    }

    public boolean contains(String item) {
        for (int i = 0; i < count; i++) {
            if (items[i].equals(item)) return true;
        }
        return false;
    }

    public int size() {
        return count;
    }
}