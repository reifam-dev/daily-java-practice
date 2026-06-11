// Day 62 - Error Finding Quiz
// Find and fix the bugs

public class Day62ErrorQuiz {

    abstract static class Vehicle {
        abstract double getMaxSpeed();
        abstract String getVehicleType();
        abstract String getFuelType();

        String describe() {
            return getVehicleType() + " | " + getFuelType()
                    + " | " + getMaxSpeed() + " km/h";
        }
    }

    static class Car extends Vehicle {
        private String model;

        Car(String model) {
            model = model;           // Bug 1 - missing this
        }

        double getMaxSpeed() { return 200.0; }
        String getVehicleType() { return "Car"; }
        String getFuelType() { return "Petrol"; }
    }

    static class Bicycle extends Vehicle {
        double getMaxSpeed() { return 40.0; }
        String getVehicleType() { return "Bicycle"; }
        // Bug 2 - missing getFuelType() implementation — abstract method not implemented
    }

    public static void main(String[] args) {
        Car car = new Car("Toyota")
        System.out.println(car.describe());  // Bug 3 - missing semicolon above

        Vehicle v = new Vehicle();           // Bug 3b - cannot instantiate abstract class
    }

}