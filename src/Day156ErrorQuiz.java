import java.util.HashMap;
import java.util.Map;

public class Day156ErrorQuiz {
    private String dealName;
    private Double marketValue;
    private Double ltv;

    public Day156ErrorQuiz withName(String name) {
        dealName = name;
    }

    public Day156ErrorQuiz withMarketValue(double value) {
        marketValue = value;
        return this;
    }

    public Day156ErrorQuiz withLtv(double ltv) {
        this.ltv = ltv;
        return this
    }

    public Map<String, Object> build() {
        Map<String, Object> result = new HashMap<>();
        result.put("dealName", dealName);
        result.put("marketValue", marketValue);
        result.put("ltv", ltv);
        return result;
    }

    public static void main(String[] args) {
        Map<String, Object> deal = new Day156ErrorQuiz()
                .withName("Riverside JV")
                .withMarketValue(12500000.0)
                .withLtv(0.60)
                .build();
        System.out.println(deal);
    }
}