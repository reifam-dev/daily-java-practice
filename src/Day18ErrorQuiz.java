// Day 18 - Simple Error Finding Quiz

public class Day18ErrorQuiz {

    public static void main(String[] args) {

        CafeOrder order = new CafeOrder();
        order.placeOrder("Coffee", 2.50);
        order.placeOrder("Cake", 3.00);
        System.out.println(order.getTotal());
        System.out.println(order.getItemCount());

    }

}