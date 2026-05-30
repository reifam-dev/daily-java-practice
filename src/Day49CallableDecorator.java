public class Day49CallableDecorator {

    public static void main(String[] args) {

        System.out.println("=== CountCalls wrapper ===\n");
        CallCounter greeter = new CallCounter("Greeter");
        System.out.println("  " + greeter.call("Hello, Alice!"));
        System.out.println("  " + greeter.call("Hello, Bob!"));
        System.out.println("  " + greeter.call("Hello, Charlie!"));
        System.out.println("  Call count : " + greeter.getCallCount());
        greeter.reset();
        System.out.println("  After reset: " + greeter.getCallCount());

        System.out.println("\n=== RateLimit wrapper ===\n");
        RateLimitedCaller api = new RateLimitedCaller("APIClient", 3);
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("  " + api.call("/endpoint/" + i));
            }
        } catch (RuntimeException e) {
            System.out.println("  Error: " + e.getMessage());
        }

    }

}

class CallCounter {

    private String name;
    private int callCount;

    public CallCounter(String name) {
        this.name = name;
        this.callCount = 0;
    }

    public String call(String message) {
        callCount++;
        return name + " [call " + callCount + "]: " + message;
    }

    public int getCallCount() {
        return callCount;
    }

    public void reset() {
        callCount = 0;
    }

    @Override
    public String toString() {
        return String.format("CallCounter(name='%s', calls=%d)",
                name, callCount);
    }

}

class RateLimitedCaller {

    private String name;
    private int maxCalls;
    private int callCount;

    public RateLimitedCaller(String name, int maxCalls) {
        this.name = name;
        this.maxCalls = maxCalls;
        this.callCount = 0;
    }

    public String call(String endpoint) {
        if (callCount >= maxCalls) {
            throw new RuntimeException(
                    "Rate limit of " + maxCalls + " calls exceeded for '"
                            + name + "'.");
        }
        callCount++;
        return name + " [call " + callCount + "/" + maxCalls
                + "]: data from " + endpoint;
    }

    public int getCallCount() {
        return callCount;
    }

}