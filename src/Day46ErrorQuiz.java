// Day 46 - Error Finding Quiz
// Find and fix the bugs

public class Day46ErrorQuiz {

    private static Day46ErrorQuiz instance = null;
    private String host;
    private int port;

    private Day46ErrorQuiz(String host, int port) {
        host = host;              // Bug 1 - missing this
        this.port = port;
    }

    public static Day46ErrorQuiz getInstance(String host, int port) {
        if (instance == null) {
            instance = new Day46ErrorQuiz(host, port);
        }
        return instance;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String toString() {
        return host + ":" + port;  // Bug 2 - host is null due to Bug 1
    }

    public static void main(String[] args) {
        Day46ErrorQuiz db1 = Day46ErrorQuiz.getInstance("localhost", 5432)
        Day46ErrorQuiz db2 = Day46ErrorQuiz.getInstance("remotehost", 9999); // Bug 3 - missing semicolon above
        System.out.println(db1 == db2);
        System.out.println(db1);
    }

}