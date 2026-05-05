public class Day25StockInventory {

    public static void main(String[] args) {

        StockItem apples = new StockItem("Apples", 20);
        StockItem bananas = new StockItem("Bananas", 3);
        StockItem oranges = new StockItem("Oranges", 8);

        System.out.println(apples.getItemName()
                + " in stock: " + apples.getQuantity());
        System.out.println("Bananas low stock: " + bananas.isLowStock());

        apples.sell(17);
        System.out.println("After selling 17 Apples: "
                + apples.getQuantity() + " left");
        System.out.println("Apples low stock: " + apples.isLowStock());

        bananas.restock(10);
        System.out.println("After restocking Bananas: "
                + bananas.getQuantity() + " total");
        System.out.println("Bananas low stock: " + bananas.isLowStock());

        apples.sell(10);   // Should print warning - insufficient stock

    }

}

class StockItem {

    private String itemName;
    private int quantity;
    private static final int LOW_STOCK_THRESHOLD = 5;

    public StockItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isLowStock() {
        return quantity < LOW_STOCK_THRESHOLD;
    }

    public void sell(int amount) {
        if (amount > quantity) {
            System.out.println("Insufficient stock for '"
                    + itemName + "'. Available: " + quantity);
        } else {
            quantity -= amount;
        }
    }

    public void restock(int amount) {
        if (amount > 0) {
            quantity += amount;
        }
    }

}