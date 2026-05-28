// Day 48 - Error Finding Quiz
// Find and fix the bugs

public class Day48ErrorQuiz {

    private String name;
    private boolean connected;

    public Day48ErrorQuiz(String name) {
        name = name;              // Bug 1 - missing this
        connected = false;
    }

    public void connect() {
        connected = true;
        System.out.println("Connected to: " + name);
    }

    public String query(String sql) {
        if (connected == false) {  // Bug 2 - should use !connected
            return "Not connected.";
        }
        return name + ": " + sql;
    }

    public static void main(String[] args) {
        Day48ErrorQuiz db = new Day48ErrorQuiz("ProductionDB")
        db.connect();              // Bug 3 - missing semicolon above
        System.out.println(db.query("SELECT * FROM users"));
    }

}