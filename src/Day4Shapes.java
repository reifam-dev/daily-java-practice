public class Day4Shapes {
    public static void main(String[] args) {
        Shape c = new Circle(5.0);
        Shape r = new Rectangle(4.0, 6.0);

        System.out.printf("Circle area: %.2f%n", c.area());
        System.out.printf("Rectangle area: %.2f%n", r.area());
    }
}

// Abstract class
abstract class Shape {
    public abstract double area();

    public String describe() {
        return "This is a " + this.getClass().getSimpleName() + " with area " + String.format("%.2f", area());
    }
}

// Concrete implementations
class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}