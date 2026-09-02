import java.util.ArrayList;
import java.util.List;

class FakeConnection {
    private int connId;
    private boolean inUse;

    public FakeConnection(int connId) {
        this.connId = connId;
        this.inUse = false;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public String execute(String query) {
        return "conn-" + connId + " executed: " + query;
    }
}

public class Day145ErrorQuiz {

    private List<FakeConnection> connections = new ArrayList<>();

    public Day145ErrorQuiz(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            connections.add(new FakeConnection(i));
        }
    }

    public FakeConnection acquire() {
        for (FakeConnection conn : connections) {
            conn.setInUse(true);
            return conn;
        }
        throw new RuntimeException("No available connections")
    }

    public void release(FakeConnection conn) {
        conn.setInUse(false);
    }

    public static void main(String[] args) {
        Day145ErrorQuiz pool = new Day145ErrorQuiz(2);

        FakeConnection connA = pool.acquire();
        System.out.println(connA.execute("SELECT * FROM deals"));

        FakeConnection connB = pool.acquire();
        System.out.println(connB.execute("SELECT * FROM investors"));

        pool.release(connA);
        System.out.println("Released connA");
    }
}