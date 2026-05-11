public class Day31ProductCatalogue {

    public static void main(String[] args) {

        Product p1 = new Product("Apple", 0.50, "Fruit");
        Product p2 = new Product("Banana", 0.30, "Fruit");
        Product p3 = new Product("Laptop", 999.99, "Electronics");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        p1.applyDiscount(10);
        System.out.println("After 10% discount:");
        System.out.println(p1);

        p3.applyDiscount(5);
        System.out.println("After 5% discount:");
        System.out.println(p3);

        p2.applyDiscount(-10);   // Should print warning - invalid discount

    }

}

class Product {

    private String name;
    private double price;
    private String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void applyDiscount(double percentage) {
        if (percentage <= 0 || percentage >= 100) {
            System.out.println("Discount must be between 0 and 100.");
        } else {
            price -= price * (percentage / 100);
        }
    }

    @Override
    public String toString() {
        return String.format("Product(name='%s', price=£%.2f, category='%s')",
                name, price, category);
    }

}