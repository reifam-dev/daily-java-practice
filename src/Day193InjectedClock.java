import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

/**
 * Dependency injection: the service never reaches out for the time
 * itself, it asks an injected clock, so a test can hand it a FakeClock
 * it fully controls. The injected clock is the one actually used, and
 * Instant is immutable, so the fake must REASSIGN its field to advance.
 */
public class Day193InjectedClock {

    private static final class FakeClock implements Supplier<Instant> {
        private Instant now;

        FakeClock(Instant now) {
            this.now = now;
        }

        @Override
        public Instant get() {
            return this.now;
        }

        void advanceDays(long days) {
            this.now = this.now.plus(Duration.ofDays(days));
        }
    }

    private static final class ValuationService {
        private final Supplier<Instant> clock;

        ValuationService() {
            this(Instant::now);
        }

        ValuationService(Supplier<Instant> clock) {
            this.clock = clock;
        }

        boolean isStale(Instant valuedAt, long maxAgeDays) {
            long ageDays = Duration.between(valuedAt, this.clock.get()).toDays();
            return ageDays > maxAgeDays;
        }
    }

    public static void main(String[] args) {
        FakeClock clock = new FakeClock(Instant.parse("2020-01-10T00:00:00Z"));
        ValuationService service = new ValuationService(clock);
        Instant valuedAt = Instant.parse("2020-01-05T00:00:00Z");

        System.out.println(service.isStale(valuedAt, 30));
        clock.advanceDays(40);
        System.out.println(clock.get());
        System.out.println(service.isStale(valuedAt, 30));
        System.out.println(new ValuationService().isStale(valuedAt, 30));
    }
}