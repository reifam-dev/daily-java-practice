import java.util.ArrayList;

public class Day62AbcRevisited {

    public static void main(String[] args) {

        ArrayList<Vehicle> fleet = new ArrayList<>();
        fleet.add(new Car("Toyota Corolla"));
        fleet.add(new ElectricCar("Tesla Model S", 600));
        fleet.add(new Bicycle("Brompton"));

        System.out.println("=== Fleet ===\n");
        for (Vehicle v : fleet) {
            System.out.println("  " + v.describe());
        }

        System.out.println("\n=== Type checks ===\n");
        for (Vehicle v : fleet) {
            System.out.println("  " + v.getVehicleType()
                    + " instanceof Vehicle: " + (v instanceof Vehicle));
        }

    }

}

abstract class Vehicle {
    abstract double getMaxSpeed();
    abstract String getVehicleType();
    abstract String getFuelType();

    String describe() {
        return String.format("%s | %s | max %.0f km/h",
                getVehicleType(), getFuelType(), getMaxSpeed());
    }
}

class Car extends Vehicle {
    private String model;

    public Car(String model) {
        this.model = model;
    }

    public double getMaxSpeed() { return 200.0; }
    public String getVehicleType() { return "Car"; }
    public String getFuelType() { return "Petrol"; }

    @Override
    public String describe() {
        return String.format("Car '%s' | %s | max %.0f km/h",
                model, getFuelType(), getMaxSpeed());
    }
}

class ElectricCar extends Vehicle {
    private String model;
    private double rangeKm;

    public ElectricCar(String model, double rangeKm) {
        this.model = model;
        this.rangeKm = rangeKm;
    }

    public double getMaxSpeed() { return 250.0; }
    public String getVehicleType() { return "Electric Car"; }
    public String getFuelType() { return "Electric"; }

    @Override
    public String describe() {
        return String.format("ElectricCar '%s' | %s | max %.0f km/h | range %.0f km",
                model, getFuelType(), getMaxSpeed(), rangeKm);
    }
}

class Bicycle extends Vehicle {
    private String brand;

    public Bicycle(String brand) {
        this.brand = brand;
    }

    public double getMaxSpeed() { return 40.0; }
    public String getVehicleType() { return "Bicycle"; }
    public String getFuelType() { return "Human-powered"; }
}