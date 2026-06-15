import java.io.*;
import java.nio.file.*;

public class Day66Contextlib {

    public static void main(String[] args) {

        System.out.println("=== try-with-resources (suppress equivalent) ===\n");

        // suppress equivalent — catch and ignore specific exception
        try {
            Files.readString(Path.of("nonexistent.txt"));
        } catch (IOException e) {
            // silently suppressed — equivalent to contextlib.suppress
            System.out.println("  File not found — suppressed gracefully.");
        }

        System.out.println("\n=== Capture output (redirect_stdout equivalent) ===\n");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));

        System.out.println("Line 1: Revenue up 12%");
        System.out.println("Line 2: Costs down 5%");

        System.setOut(original);
        String captured = baos.toString();
        System.out.println("  Captured output:");
        for (String line : captured.split("\n")) {
            System.out.println("    " + line.trim());
        }

        System.out.println("\n=== Multiple resources (ExitStack equivalent) ===\n");

        try {
            Path tmp1 = Files.createTempFile("report_a", ".txt");
            Path tmp2 = Files.createTempFile("report_b", ".txt");

            try (BufferedWriter w1 = Files.newBufferedWriter(tmp1);
                 BufferedWriter w2 = Files.newBufferedWriter(tmp2)) {
                w1.write("Content of report A");
                w2.write("Content of report B");
            }

            System.out.println("  " + tmp1.getFileName()
                    + ": " + Files.readString(tmp1));
            System.out.println("  " + tmp2.getFileName()
                    + ": " + Files.readString(tmp2));

            Files.delete(tmp1);
            Files.delete(tmp2);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}