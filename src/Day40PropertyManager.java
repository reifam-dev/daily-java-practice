public class Day40PropertyManager {

    public static void main(String[] args) {

        Temperature t = new Temperature(100.0);
        System.out.println("Celsius    : " + t.getCelsius());
        System.out.printf("Fahrenheit : %.2f%n", t.getFahrenheit());

        t.setCelsius(0.0);
        System.out.println("\nAfter setting to 0°C:");
        System.out.println("Celsius    : " + t.getCelsius());
        System.out.printf("Fahrenheit : %.2f%n", t.getFahrenheit());

        t.setCelsius(-300.0);   // Should print warning

        System.out.println();

        Circle c = new Circle(5.0);
        System.out.printf("Radius        : %.2f%n", c.getRadius());
        System.out.printf("Diameter      : %.2f%n", c.getDiameter());
        System.out.printf("Area          : %.2f%n", c.getArea());
        System.out.printf("Circumference : %.2f%n", c.getCircumference());

        c.setRadius(10.0);
        System.out.printf("%nAfter radius=10 — Area: %.2f%n", c.getArea());

        c.setRadius(-5.0);   // Should print warning

    }

}

class Temperature {

    private static final double ABSOLUTE_ZERO = -273.15;
    private double celsius;

    public Temperature(double celsius) {
        this.celsius = celsius;
    }

    public double getCelsius() {
        return celsius;
    }

    public void setCelsius(double value) {
        if (value < ABSOLUTE_ZERO) {
            System.out.printf("Temperature %.2f°C is below absolute zero.%n", value);
            return;
        }
        this.celsius = value;
    }

    public double getFahrenheit() {
        return celsius * 9.0 / 5.0 + 32;
    }

}

class Circle {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double value) {
        if (value < 0) {
            System.out.println("Radius cannot be negative.");
            return;
        }
        this.radius = value;
    }

    public double getDiameter() {
        return radius * 2;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

}