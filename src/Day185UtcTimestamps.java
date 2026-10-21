import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Store and compare moments as zone-aware UTC values. Duration.toHours()
 * is the whole elapsed duration, while toHoursPart() is only the hours
 * component (0-23) with the days dropped. Instant.now() is an
 * unambiguous UTC moment; LocalDateTime.now() has no zone at all.
 */
public class Day185UtcTimestamps {

    private static ZonedDateTime makeValuationTimestamp(int year, int month, int day) {
        return ZonedDateTime.of(year, month, day, 12, 0, 0, 0, ZoneOffset.UTC);
    }

    private static long hoursBetween(ZonedDateTime start, ZonedDateTime end) {
        return Duration.between(start, end).toHours();
    }

    private static Instant nowUtc() {
        return Instant.now();
    }

    public static void main(String[] args) {
        ZonedDateTime start = ZonedDateTime.of(2026, 10, 1, 9, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime end = start.plusDays(1).plusHours(2);
        System.out.println(hoursBetween(start, end));
        System.out.println(makeValuationTimestamp(2026, 10, 5));
        System.out.println(nowUtc().isAfter(start.toInstant()));
    }
}