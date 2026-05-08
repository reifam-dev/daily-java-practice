public class Day28TicketBooking {

    public static void main(String[] args) {

        Ticket t1 = new Ticket(101, "Alice");
        Ticket t2 = new Ticket(102, "Bob");
        Ticket t3 = new Ticket(103, "Charlie");

        System.out.println("Ticket " + t1.getTicketNumber()
                + " - " + t1.getTicketHolder()
                + " - booked: " + t1.isBooked());

        t2.cancel();
        System.out.println("After cancelling Bob - booked: "
                + t2.isBooked());

        t2.rebook("David");
        System.out.println("After rebooking for David - holder: "
                + t2.getTicketHolder()
                + " - booked: " + t2.isBooked());

        t3.cancel();
        t3.cancel();   // Should print warning - already cancelled

    }

}

class Ticket {

    private int ticketNumber;
    private String ticketHolder;
    private boolean booked;

    public Ticket(int ticketNumber, String ticketHolder) {
        this.ticketNumber = ticketNumber;
        this.ticketHolder = ticketHolder;
        this.booked = true;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getTicketHolder() {
        return ticketHolder;
    }

    public boolean isBooked() {
        return booked;
    }

    public void cancel() {
        if (!booked) {
            System.out.println("Ticket " + ticketNumber
                    + " is already cancelled.");
        } else {
            booked = false;
            ticketHolder = "";
        }
    }

    public void rebook(String newHolder) {
        this.ticketHolder = newHolder;
        this.booked = true;
    }

}