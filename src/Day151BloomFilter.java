import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day151BloomFilter {
    private final int size;
    private final int numHashes;
    private final boolean[] bits;

    public Day151BloomFilter(int size, int numHashes) {
        if (size <= 0 || numHashes <= 0) {
            throw new IllegalArgumentException("size and numHashes must be positive");
        }
        this.size = size;
        this.numHashes = numHashes;
        this.bits = new boolean[size];
    }

    private int hashAt(String item, int i) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] h = digest.digest((item + i).getBytes(StandardCharsets.UTF_8));
            long unsignedValue = 0;
            for (int b = 0; b < 4; b++) {
                unsignedValue = (unsignedValue << 8) | (h[b] & 0xFF);
            }
            return (int) (Math.abs(unsignedValue) % this.size);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public void add(String item) {
        for (int i = 0; i < this.numHashes; i++) {
            this.bits[hashAt(item, i)] = true;
        }
    }

    public boolean mightContain(String item) {
        for (int i = 0; i < this.numHashes; i++) {
            if (!this.bits[hashAt(item, i)]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Day151BloomFilter bf = new Day151BloomFilter(1000, 3);
        bf.add("Riverside JV");
        bf.add("Westgate Retail");

        System.out.println(bf.mightContain("Riverside JV"));
        System.out.println(bf.mightContain("Logistics Portfolio"));
    }
}