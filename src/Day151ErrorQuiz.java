import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day151ErrorQuiz {
    private int size;
    private int numHashes;
    private boolean[] bits;

    public Day151ErrorQuiz(int size, int numHashes) {
        this.size = size;
        this.numHashes = numHashes;
        this.bits = new boolean[size];
    }

    private int hashAt(String item, int i) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] h = digest.digest((item + i).getBytes(StandardCharsets.UTF_8));
            return Math.abs(h[0]) % size;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public void add(String item) {
        for (int i = 0; i <= numHashes; i++) {
            bits[hashAt(item, i)] = true;
        }
    }

    public boolean mightContain(String item) {
        for (int i = 0; i < numHashes; i++) {
            if (!bits[hashAt(item, i)]) {
                return false
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Day151ErrorQuiz bf = new Day151ErrorQuiz(100, 3);
        bf.add("Riverside JV");
        System.out.println(bf.mightContain("Riverside JV"));
    }
}