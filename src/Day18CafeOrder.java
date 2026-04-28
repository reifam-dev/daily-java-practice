public class Day18CafeOrder {

    public static void main(String[] args) {

        CafeOrder order = new CafeOrder();
        order.placeOrder("Coffee", 2.50);
        order.placeOrder("Cake", 3.00);
        order.placeOrder("Orange Juice", 2.00);

        System.out.println("Orders     : " + order.listOrders());
        System.out.println("Items      : " + order.getItemCount());
        System.out.printf("Total      : £%.2f%n", order.getTotal());

        order.cancelOrder("Cake");
        System.out.printf("After cancelling Cake: £%.2f%n", order.getTotal());

    }

}

class CafeOrder {

    private String[] items = new String[50];
    private double[] prices = new double[50];
    private int count = 0;

    public void placeOrder(String item, double price) {
        if (isOrdered(item)) {
            System.out.println(item + " is already in the order.");
            return;
        }
        if (count < items.length) {
            items[count] = item;
            prices[count] = price;
            count++;
        }
    }

    public void cancelOrder(String item) {
        for (int i = 0; i < count; i++) {
            if (items[i].equals(item)) {
                for (int j = i; j < count - 1; j++) {
                    items[j] = items[j + 1];
                    prices[j] = prices[j + 1];
                }
                count--;
                return;
            }
        }
        System.out.println(item + " is not in the order.");
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += prices[i];
        }
        return total;
    }

    public String listOrders() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < count; i++) {
            sb.append(items[i]);
            if (i < count - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean isOrdered(String item) {
        for (int i = 0; i < count; i++) {
            if (items[i].equals(item)) return true;
        }
        return false;
    }

    public int getItemCount() {
        return count;
    }

}