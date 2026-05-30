public class Day50Slots {

    public static void main(String[] args) {

        System.out.println("=== Immutable Point with final fields ===\n");

        ImmutablePoint p1 = new ImmutablePoint(3, 4);
        ImmutablePoint p2 = new ImmutablePoint(6, 8);
        ImmutablePoint p3 = new ImmutablePoint(3, 4);

        System.out.println("  p1 = " + p1);
        System.out.println("  p2 = " + p2);
        System.out.printf("  p1 distance : %.2f%n", p1.distanceFromOrigin());
        System.out.printf("  p2 distance : %.2f%n", p2.distanceFromOrigin());

        System.out.println("  p1.equals(p3) : " + p1.equals(p3));
        System.out.println("  p1.equals(p2) : " + p1.equals(p2));
        System.out.println("  p1 == p3      : " + (p1 == p3));

        System.out.println("\n=== Immutable Colour ===\n");

        ImmutableColour red = new ImmutableColour(255, 0, 0);
        ImmutableColour green = new ImmutableColour(0, 255, 0);

        System.out.println("  Red   : " + red);
        System.out.println("  Green : " + green);

    }

}

final class ImmutablePoint {

    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    public ImmutablePoint translate(int dx, int dy) {
        return new ImmutablePoint(x + dx, y + dy);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ImmutablePoint)) return false;
        ImmutablePoint other = (ImmutablePoint) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return String.format("Point(%d, %d)", x, y);
    }

}

final class ImmutableColour {

    private final int red;
    private final int green;
    private final int blue;

    public ImmutableColour(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255
                || blue < 0 || blue > 255) {
            throw new IllegalArgumentException(
                    "Colour values must be between 0 and 255.");
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() { return red; }
    public int getGreen() { return green; }
    public int getBlue() { return blue; }

    public String toHex() {
        return String.format("#%02x%02x%02x", red, green, blue);
    }

    @Override
    public String toString() {
        return String.format("Colour(%d, %d, %d) = %s",
                red, green, blue, toHex());
    }

}