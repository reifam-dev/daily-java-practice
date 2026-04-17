public class Day7TemperatureConverter {
    public static void main(String[] args) {
        TemperatureConverter conv = new TemperatureConverter();
        System.out.println("0°C = " + conv.celsiusToFahrenheit(0) + "°F");
        System.out.println("32°F = " + conv.fahrenheitToCelsius(32) + "°C");
    }
}

class TemperatureConverter {
    public double celsiusToFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32;
    }

    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5.0 / 9.0;
    }
}