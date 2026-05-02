public class Day22HotelBooking {

    public static void main(String[] args) {

        HotelRoom room101 = new HotelRoom(101);
        HotelRoom room102 = new HotelRoom(102);
        HotelRoom room103 = new HotelRoom(103);

        System.out.println("Room 101 available: " + room101.isAvailable());

        room101.book();
        room102.book();

        System.out.println("After booking 101 and 102:");
        System.out.println("Room 101 available: " + room101.isAvailable());
        System.out.println("Room 103 available: " + room103.isAvailable());

        room101.checkOut();
        System.out.println("After checkout 101:");
        System.out.println("Room 101 available: " + room101.isAvailable());

        room102.book();   // Should print warning - already booked

    }

}

class HotelRoom {

    private int roomNumber;
    private boolean booked;

    public HotelRoom(int roomNumber) {
        this.roomNumber = roomNumber;
        this.booked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return !booked;
    }

    public void book() {
        if (booked) {
            System.out.println("Room " + roomNumber + " is already booked.");
        } else {
            booked = true;
        }
    }

    public void checkOut() {
        if (!booked) {
            System.out.println("Room " + roomNumber + " is not booked.");
        } else {
            booked = false;
        }
    }

}