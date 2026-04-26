public class Day16CinemaBooking {

    public static void main(String[] args) {

        CinemaBooking cinema = new CinemaBooking(100);
        cinema.bookSeat(5);
        cinema.bookSeat(10);
        cinema.bookSeat(42);

        System.out.println("Seat 5 available  : " + cinema.isAvailable(5));
        System.out.println("Seat 7 available  : " + cinema.isAvailable(7));
        System.out.println("Available seats   : " + cinema.getAvailableCount());

        cinema.cancelBooking(10);
        System.out.println("After cancelling seat 10, available: "
                + cinema.getAvailableCount());

    }

}

class CinemaBooking {

    private int[] bookedSeats = new int[200];
    private int count = 0;
    private int totalSeats;

    public CinemaBooking(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void bookSeat(int seatNumber) {
        if (!isAvailable(seatNumber)) {
            System.out.println("Seat " + seatNumber + " is already booked.");
            return;
        }
        if (count < bookedSeats.length) {
            bookedSeats[count] = seatNumber;
            count++;
        }
    }

    public void cancelBooking(int seatNumber) {
        for (int i = 0; i < count; i++) {
            if (bookedSeats[i] == seatNumber) {
                for (int j = i; j < count - 1; j++) {
                    bookedSeats[j] = bookedSeats[j + 1];
                }
                count--;
                return;
            }
        }
        System.out.println("Seat " + seatNumber + " was not booked.");
    }

    public boolean isAvailable(int seatNumber) {
        for (int i = 0; i < count; i++) {
            if (bookedSeats[i] == seatNumber) return false;
        }
        return true;
    }

    public int getAvailableCount() {
        return totalSeats - count;
    }

}