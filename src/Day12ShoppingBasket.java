public class Day12ShoppingBasket {

    public static void main(String[] args) {

        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem("apple", 0.50);
        basket.addItem("bread", 1.20);
        basket.addItem("milk", 0.90);

        System.out.printf("Total: £%.2f%n", basket.getTotal());
        System.out.println("Item count: " + basket.getItemCount());

    }

}

class ShoppingBasket {

    private String[] names = new String[20];
    private double[] prices = new double[20];
    private int count = 0;

    public void addItem(String name, double price) {
        if (count < names.length) {
            names[count] = name;
            prices[count] = price;
            count++;
        }
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += prices[i];
        }
        return total;
    }

    public int getItemCount() {
        return count;
    }

}