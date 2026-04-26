// Day 16 - Simple Error Finding Quiz

public class Day16ErrorQuiz {

    public static void main(String[] args) {

        CinemaBooking cinema = new CinemaBooking(100);
        cinema.bookSeat(5);
        cinema.bookSeat(10);
        System.out.println(cinema.isAvailable(5));
        System.out.println(cinema.getAvailableCount());

    }

}