public class Day33OnlineShop {

    public static void main(String[] args) {

        ShopItem apple = new ShopItem("Apple", 0.50, 20);
        ShopItem bread = new ShopItem("Bread", 1.20, 10);
        ShopItem milk = new ShopItem("Milk", 0.90, 15);

        System.out.println(apple);
        System.out.println(bread);
        System.out.println(milk);

        System.out.printf("%nTotal value of apples: £%.2f%n",
                apple.getTotalValue());

        apple.restock(10);
        System.out.println("\nAfter restocking apples:");
        System.out.println(apple);

        bread.sell(4);
        System.out.println("\nAfter selling 4 breads:");
        System.out.println(bread);

        milk.sell(20);   // Should print warning - insufficient stock

    }

}

class ShopItem {

    private String name;
    private double price;
    private int quantity;

    public ShopItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    public void restock(int amount) {
        if (amount > 0) {
            quantity += amount;
        } else {
            System.out.println("Restock amount must be positive.");
        }
    }

    public void sell(int amount) {
        if (amount > quantity) {
            System.out.println("Insufficient stock for '"
                    + name + "'. Available: " + quantity);
        } else {
            quantity -= amount;
        }
    }

    @Override
    public String toString() {
        return String.format("ShopItem(name='%s', price=£%.2f, qty=%d)",
                name, price, quantity);
    }

}