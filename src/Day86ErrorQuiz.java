// This file contains 3 deliberate bugs. Find and fix them.
public class Day86ErrorQuiz {

    private String name;
    private double value;
    private boolean readOnly;

    public Day86ErrorQuiz(String name, double value, boolean readOnly) {
        name = name;                            // Bug 1: missing this
        this.value = value;
        this.readOnly = readOnly;
    }

    public void setValue(double value) {
        if (this.readOnly) {
            throw new UnsupportedOperationException("Attribute is read-only.");
        }
        if (value =< 0) {                       // Bug 2: =< should be <=
            throw new IllegalArgumentException("Value must be positive: " + value);
        }
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void printDescriptor() {
        System.out.println("Name     : " + this.name);
        System.out.println("Value    : " + this.value);
        System.out.println("ReadOnly : " + this.readOnly)
    }                                           // Bug 3: missing semicolon

    @Override
    public String toString() {
        return "Descriptor: " + name + " | value=" + value + " | readOnly=" + readOnly;
    }

    public static void main(String[] args) {
        Day86ErrorQuiz d = new Day86ErrorQuiz("yield_pct", 4.5, false);
        System.out.println(d);
        d.setValue(5.0);
        d.printDescriptor();
        try {
            d.setValue(-1.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}