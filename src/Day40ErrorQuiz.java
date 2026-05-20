// Day 40 - Error Finding Quiz
// Find and fix the bugs

public class Day40ErrorQuiz {

    private double celsius;

    public Day40ErrorQuiz(double celsius) {
        celsius = celsius;             // Bug 1 - missing this
    }

    public double getCelsius() {
        return celsius;
    }

    public void setCelsius(double value) {
        if (value < -273.15) {
            System.out.println("Below absolute zero.");
            return;
        }
        celsius =+ value;              // Bug 2 - wrong operator, should be =
    }

    public double getFahrenheit() {
        return celsius * 9 / 5 + 32;
    }

    public static void main(String[] args) {
        Day40ErrorQuiz t = new Day40ErrorQuiz(100.0)
        System.out.println(t.getCelsius());     // Bug 3 - missing semicolon above
        t.setCelsius(200.0);
        System.out.println(t.getCelsius());
        System.out.println(t.getFahrenheit());
    }

}