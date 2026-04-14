// Day 4 - Error Finding Quiz (Fix abstract class/interface issues)

public class Day4ErrorQuiz {
    public static void main(String[] args) {
        Circle c = new Circle(5);
        Rectangle r = new Rectangle(4, 6);
        System.out.println(c.area() + " " + r.area());
    }
}

abstract class Shape {
    public abstract double area();
}

class Circle extends Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
    // missing area() implementation
}

class Rectangle extends Shape {
    double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
}