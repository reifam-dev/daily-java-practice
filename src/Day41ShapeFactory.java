public class Day41ShapeFactory {

    public static void main(String[] args) {

        Shape rectangle = new Rectangle(4.0, 6.0);
        Shape circle = new Circle(5.0);
        Shape triangle = new Triangle(3.0, 4.0, 5.0);

        Shape[] shapes = {rectangle, circle, triangle};

        System.out.println("=== All shapes ===\n");
        for (Shape s : shapes) {
            System.out.println("  " + s);
        }

        System.out.println("\n=== instanceof checks ===\n");
        for (Shape s : shapes) {
            System.out.println("  " + s.getName()
                    + " instanceof Shape: " + (s instanceof Shape));
        }

    }

}

abstract class Shape {

    public abstract double area();
    public abstract double perimeter();
    public abstract String getName();

    @Override
    public String toString() {
        return String.format("%s: area=%.2f, perimeter=%.2f",
                getName(), area(), perimeter());
    }

}

class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String getName() { return "Rectangle"; }

    @Override
    public double area() { return width * height; }

    @Override
    public double perimeter() { return 2 * (width + height); }

}

class Circle extends Shape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String getName() { return "Circle"; }

    @Override
    public double area() { return Math.PI * radius * radius; }

    @Override
    public double perimeter() { return 2 * Math.PI * radius; }

}

class Triangle extends Shape {

    private double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String getName() { return "Triangle"; }

    @Override
    public double area() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double perimeter() { return a + b + c; }

}