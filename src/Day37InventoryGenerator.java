import java.util.ArrayList;

public class Day37InventoryGenerator {

    public static void main(String[] args) {

        ArrayList<StockItem> inventory = new ArrayList<>();
        inventory.add(new StockItem("Apple", 3));
        inventory.add(new StockItem("Banana", 10));
        inventory.add(new StockItem("Mango", 2));
        inventory.add(new StockItem("Orange", 7));
        inventory.add(new StockItem("Grape", 1));

        System.out.println("All items:");
        for (StockItem item : inventory) {
            System.out.println("  " + item);
        }

        System.out.println("\nLow stock items (below 5):");
        for (StockItem item : inventory) {
            if (item.isLowStock()) {
                System.out.println("  " + item);
            }
        }

        System.out.println("\nRestocking Apple by 10:");
        inventory.get(0).restock(10);
        System.out.println("  " + inventory.get(0));

        System.out.println("\nLow stock after restock:");
        boolean anyLow = false;
        for (StockItem item : inventory) {
            if (item.isLowStock()) {
                System.out.println("  " + item);
                anyLow = true;
            }
        }
        if (!anyLow) {
            System.out.println("  None");
        }

    }

}

class StockItem {

    private String name;
    private int quantity;
    private static final int LOW_STOCK_THRESHOLD = 5;

    public StockItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isLowStock() {
        return quantity < LOW_STOCK_THRESHOLD;
    }

    public void restock(int amount) {
        if (amount > 0) {
            quantity += amount;
        } else {
            System.out.println("Restock amount must be positive.");
        }
    }

    @Override
    public String toString() {
        return String.format("StockItem(name='%s', qty=%d, lowStock=%b)",
                name, quantity, isLowStock());
    }

}