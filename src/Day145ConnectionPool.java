import java.util.ArrayList;
import java.util.List;

final class FakeConnection {
    private final int connId;
    private boolean inUse;

    public FakeConnection(int connId) {
        this.connId = connId;
        this.inUse = false;
    }

    public boolean isInUse() {
        return this.inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public String execute(String query) {
        return "conn-" + this.connId + " executed: " + query;
    }
}

final class PoolExhaustedException extends RuntimeException {
    public PoolExhaustedException(String message) {
        super(message);
    }
}

/**
 * A fixed-size pool of reusable connections. acquire() only hands out
 * a connection that isn't already in use, and rejects clearly once
 * the pool is exhausted.
 */
public class Day145ConnectionPool {

    private final List<FakeConnection> connections;

    public Day145ConnectionPool(int poolSize) {
        if (poolSize <= 0) {
            throw new IllegalArgumentException("poolSize must be positive");
        }
        this.connections = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            this.connections.add(new FakeConnection(i));
        }
    }

    public FakeConnection acquire() {
        for (FakeConnection conn : this.connections) {
            if (!conn.isInUse()) {
                conn.setInUse(true);
                return conn;
            }
        }
        throw new PoolExhaustedException("No available connections");
    }

    public void release(FakeConnection conn) {
        conn.setInUse(false);
    }

    public static void main(String[] args) {
        Day145ConnectionPool pool = new Day145ConnectionPool(2);

        FakeConnection connA = pool.acquire();
        System.out.println(connA.execute("SELECT * FROM deals"));

        FakeConnection connB = pool.acquire();
        System.out.println(connB.execute("SELECT * FROM investors"));

        try {
            pool.acquire();
        } catch (PoolExhaustedException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        pool.release(connA);
        System.out.println("Released connA");

        FakeConnection connC = pool.acquire();
        System.out.println(connC.execute("SELECT * FROM deals WHERE region = 'London'"));
    }
}