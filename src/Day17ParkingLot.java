public class Day17ParkingLot {

    public static void main(String[] args) {

        ParkingLot lot = new ParkingLot(3);
        lot.parkCar("AB12CDE");
        lot.parkCar("XY99ZZZ");

        System.out.println("Parked cars      : " + lot.getOccupiedSpaces());
        System.out.println("Available spaces : " + lot.getAvailableSpaces());
        System.out.println("Space available  : " + lot.isSpaceAvailable());

        lot.removeCar("AB12CDE");
        System.out.println("After removing AB12CDE, occupied: "
                + lot.getOccupiedSpaces());

    }

}

class ParkingLot {

    private String[] parkedCars = new String[100];
    private int count = 0;
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public void parkCar(String registration) {
        if (count >= capacity) {
            System.out.println("Parking lot is full.");
            return;
        }
        if (isParked(registration)) {
            System.out.println(registration + " is already parked.");
            return;
        }
        parkedCars[count] = registration;
        count++;
    }

    public void removeCar(String registration) {
        for (int i = 0; i < count; i++) {
            if (parkedCars[i].equals(registration)) {
                for (int j = i; j < count - 1; j++) {
                    parkedCars[j] = parkedCars[j + 1];
                }
                parkedCars[count - 1] = null;
                count--;
                return;
            }
        }
        System.out.println(registration + " not found in parking lot.");
    }

    public boolean isSpaceAvailable() {
        return count < capacity;
    }

    public boolean isParked(String registration) {
        for (int i = 0; i < count; i++) {
            if (parkedCars[i].equals(registration)) return true;
        }
        return false;
    }

    public int getOccupiedSpaces() {
        return count;
    }

    public int getAvailableSpaces() {
        return capacity - count;
    }

}