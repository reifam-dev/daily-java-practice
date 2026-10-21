import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Day185ErrorQuiz {

    private static ZonedDateTime makeValuationTimestamp(int year, int month, int day) {
        return ZonedDateTime.of(year, month, day, 12, 0, 0, 0, ZoneOffset.UTC);
    }

    private static long hoursBetween(ZonedDateTime start, ZonedDateTime end) {
        return Duration.between(start, end).toHoursPart()
    }

    private static LocalDateTime nowTimestamp() {
        return LocalDateTime.now();
    }

    public static void main(String[] args) {
        ZonedDateTime start = ZonedDateTime.of(2026, 10, 1, 9, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime end = start.plusDays(1).plusHours(2);
        System.out.println(hoursBetween(start, end));
        System.out.println(makeValuationTimestamp(2026, 10, 5));
        System.out.println(nowTimestamp());
    }
}