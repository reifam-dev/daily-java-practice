import java.math.BigDecimal;

public class Day177ErrorQuiz {

    private static BigDecimal applyRate(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate)
    }

    private static boolean sameAmount(BigDecimal a, BigDecimal b) {
        return a.equals(b);
    }

    public static void main(String[] args) {
        System.out.println(applyRate(new BigDecimal(0.1), new BigDecimal("3")));
        System.out.println(sameAmount(new BigDecimal("2.0"), new BigDecimal("2.00")));
    }
}