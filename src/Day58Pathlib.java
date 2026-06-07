import java.io.*;
import java.nio.file.*;

public class Day58Pathlib {

    public static void main(String[] args) {

        try {
            System.out.println("=== Write and read file ===\n");
            Path report = Files.createTempFile("report", ".txt");

            String content = "Line 1: Revenue\nLine 2: Costs\nLine 3: Profit";
            Files.writeString(report, content);

            String readBack = Files.readString(report);
            System.out.println("  Content:\n  " + readBack);

            System.out.println("\n=== Read lines ===\n");
            for (String line : Files.readAllLines(report)) {
                System.out.println("  " + line);
            }

            System.out.println("\n=== Path properties ===\n");
            System.out.println("  exists    : " + Files.exists(report));
            System.out.println("  isFile    : " + Files.isRegularFile(report));
            System.out.println("  size      : " + Files.size(report) + " bytes");
            System.out.println("  fileName  : " + report.getFileName());
            System.out.println("  parent    : " + report.getParent());

            System.out.println("\n=== try-with-resources file write ===\n");
            Path output = Files.createTempFile("output", ".txt");
            try (BufferedWriter bw = Files.newBufferedWriter(output)) {
                bw.write("Hello from Java!");
                bw.newLine();
                bw.write("Second line.");
            }
            System.out.println("  Written: " + Files.readString(output));

            Files.delete(report);
            Files.delete(output);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}