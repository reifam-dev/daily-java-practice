public class Day26AppointmentScheduler {

    public static void main(String[] args) {

        Appointment appt1 = new Appointment("09:00", "Alice");
        Appointment appt2 = new Appointment("10:00", "Bob");
        Appointment appt3 = new Appointment("11:00", "Charlie");

        System.out.println(appt1.getTimeSlot()
                + " - " + appt1.getPatientName()
                + " - booked: " + appt1.isBooked());

        appt2.cancel();
        System.out.println("After cancelling 10:00 - booked: "
                + appt2.isBooked());

        appt2.rebook("David");
        System.out.println("After rebooking 10:00 for David - booked: "
                + appt2.isBooked()
                + ", patient: " + appt2.getPatientName());

        appt3.cancel();
        appt3.cancel();   // Should print warning - already cancelled

    }

}

class Appointment {

    private String timeSlot;
    private String patientName;
    private boolean booked;

    public Appointment(String timeSlot, String patientName) {
        this.timeSlot = timeSlot;
        this.patientName = patientName;
        this.booked = true;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getPatientName() {
        return patientName;
    }

    public boolean isBooked() {
        return booked;
    }

    public void cancel() {
        if (!booked) {
            System.out.println("Appointment at " + timeSlot
                    + " is already cancelled.");
        } else {
            booked = false;
            patientName = "";
        }
    }

    public void rebook(String newPatientName) {
        this.patientName = newPatientName;
        this.booked = true;
    }

}