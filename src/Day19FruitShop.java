public class Day19FruitShop {

    public static void main(String[] args) {

        Fruit apple = new Fruit("Apple", 0.50);
        Fruit banana = new Fruit("Banana", 0.30);
        Fruit mango = new Fruit("Mango", 1.20);

        System.out.println(apple.getName() + " costs £" + apple.getPrice());
        System.out.println(banana.getName() + " costs £" + banana.getPrice());
        System.out.println(mango.getName() + " costs £" + mango.getPrice());

        apple.setPrice(0.60);
        System.out.println("Updated Apple price: £" + apple.getPrice());

    }

}

class Fruit {

    private String name;
    private double price;

    public Fruit(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}