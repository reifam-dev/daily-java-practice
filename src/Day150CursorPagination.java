import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day150CursorPagination {
    private static final List<Integer> DEALS = new ArrayList<>();
    static { for (int i = 1; i <= 25; i++) DEALS.add(i); }

    private static Map<String, Object> getPage(int cursor, int pageSize) {
        int end = Math.min(cursor + pageSize, DEALS.size());
        List<Integer> pageItems = DEALS.subList(cursor, end);
        int nextCursor = cursor + pageSize;
        boolean hasMore = nextCursor < DEALS.size();

        Map<String, Object> result = new HashMap<>();
        result.put("items", pageItems);
        result.put("nextCursor", hasMore ? nextCursor : null);
        return result;
    }

    public static void main(String[] args) {
        int cursor = 0;
        while (true) {
            Map<String, Object> page = getPage(cursor, 5);
            System.out.println("cursor=" + cursor + ": " + ((List<?>) page.get("items")).size() + " items");
            Integer next = (Integer) page.get("nextCursor");
            if (next == null) break;
            cursor = next;
        }
    }
}