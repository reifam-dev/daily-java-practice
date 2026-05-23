import java.util.ArrayList;

public class Day43ObserverPattern {

    public static void main(String[] args) {

        NotificationSubject subject = new NotificationSubject();

        EmailObserver email = new EmailObserver("alice@example.com");
        SMSObserver sms = new SMSObserver("+44123456789");
        LogObserver log = new LogObserver();

        subject.register(email);
        subject.register(sms);
        subject.register(log);

        System.out.println("Observers: " + subject.getCount() + "\n");

        System.out.println("=== Notification 1 ===");
        subject.notifyAll("System update available");

        subject.unregister(sms);
        System.out.println("\nAfter unregistering SMS — observers: "
                + subject.getCount() + "\n");

        System.out.println("=== Notification 2 ===");
        subject.notifyAll("Maintenance tonight");

        System.out.println("\nLog history:");
        for (String entry : log.getLog()) {
            System.out.println("  " + entry);
        }

    }

}

interface Observer {
    void update(String message);
}

class NotificationSubject {

    private ArrayList<Observer> observers = new ArrayList<>();

    public void register(Observer o) {
        observers.add(o);
    }

    public void unregister(Observer o) {
        observers.remove(o);
    }

    public void notifyAll(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    public int getCount() {
        return observers.size();
    }

}

class EmailObserver implements Observer {
    private String email;
    public EmailObserver(String email) { this.email = email; }
    public void update(String message) {
        System.out.println("  Email to " + email + ": " + message);
    }
}

class SMSObserver implements Observer {
    private String phone;
    public SMSObserver(String phone) { this.phone = phone; }
    public void update(String message) {
        System.out.println("  SMS to " + phone + ": " + message);
    }
}

class LogObserver implements Observer {
    private ArrayList<String> log = new ArrayList<>();
    public void update(String message) {
        log.add(message);
        System.out.println("  Log: " + message);
    }
    public ArrayList<String> getLog() { return log; }
}