public class Day24VehicleTracker {

    public static void main(String[] args) {

        Vehicle v1 = new Vehicle("AB12CDE");
        Vehicle v2 = new Vehicle("XY99ZZZ");
        Vehicle v3 = new Vehicle("LM55PQR");

        System.out.println(v1.getRegistration() + " active: " + v1.isActive());
        System.out.println(v2.getRegistration() + " active: " + v2.isActive());

        v1.deregister();
        System.out.println("After deregistering AB12CDE:");
        System.out.println(v1.getRegistration() + " active: " + v1.isActive());

        v1.reregister();
        System.out.println("After re-registering AB12CDE:");
        System.out.println(v1.getRegistration() + " active: " + v1.isActive());

        v2.deregister();
        v2.deregister();   // Should print warning - already inactive

    }

}

class Vehicle {

    private String registration;
    private boolean active;

    public Vehicle(String registration) {
        this.registration = registration;
        this.active = true;
    }

    public String getRegistration() {
        return registration;
    }

    public boolean isActive() {
        return active;
    }

    public void deregister() {
        if (!active) {
            System.out.println(registration + " is already inactive.");
        } else {
            active = false;
        }
    }

    public void reregister() {
        active = true;
    }

}