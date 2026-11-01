import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

public class Day193ErrorQuiz {

    private static final class FakeClock implements Supplier<Instant> {
        private Instant now;

        FakeClock(Instant now) {
            this.now = now;
        }

        @Override
        public Instant get() {
            return now;
        }

        void advanceDays(long days) {
            now.plus(Duration.ofDays(days));
        }
    }

    private static final class ValuationService {
        private final Supplier<Instant> clock;

        ValuationService(Supplier<Instant> clock) {
            this.clock = clock;
        }

        boolean isStale(Instant valuedAt, long maxAgeDays) {
            long ageDays = Duration.between(valuedAt, Instant.now()).toDays();
            return ageDays > maxAgeDays
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
    }
}