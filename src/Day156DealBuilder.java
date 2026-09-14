import java.util.HashMap;
import java.util.Map;

final class IncompleteBuilderException extends RuntimeException {
    public IncompleteBuilderException(String message) { super(message); }
}

public class Day156DealBuilder {
    private String dealName;
    private Double marketValue;
    private Double ltv;

    public Day156DealBuilder withName(String name) {
        this.dealName = name;
        return this;
    }

    public Day156DealBuilder withMarketValue(double value) {
        this.marketValue = value;
        return this;
    }

    public Day156DealBuilder withLtv(double ltv) {
        this.ltv = ltv;
        return this;
    }

    public Map<String, Object> build() {
        if (this.dealName == null || this.marketValue == null) {
            throw new IncompleteBuilderException("dealName and marketValue are required");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("dealName", this.dealName);
        result.put("marketValue", this.marketValue);
        result.put("ltv", this.ltv);
        return result;
    }

    public static void main(String[] args) {
        Map<String, Object> deal = new Day156DealBuilder()
                .withName("Riverside JV")
                .withMarketValue(12500000.0)
                .withLtv(0.60)
                .build();
        System.out.println(deal);
    }
}