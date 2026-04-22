// Day 12 - Simple Error Finding Quiz

public class Day12ErrorQuiz {

    public static void main(String[] args) {

        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem("apple", 0.50);
        basket.addItem("bread", 1.20);
        System.out.println(basket.getTotal());

    }

}