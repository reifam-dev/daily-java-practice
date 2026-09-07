import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day150ErrorQuiz {
    private static final List<Integer> DEALS = new ArrayList<>();
    static { for (int i = 1; i <= 25; i++) DEALS.add(i); }

    private static Map<String, Object> getPage(int cursor, int pageSize) {
        List<Integer> pageItems = DEALS.subList(cursor, pageSize);
        int nextCursor = cursor + pageSize;
        Map<String, Object> result = new HashMap<>();
        result.put("items", pageItems);
        result.put("nextCursor", nextCursor)
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getPage(0, 5));
    }
}