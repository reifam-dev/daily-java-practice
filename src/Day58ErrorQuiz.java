// Day 58 - Error Finding Quiz
// Find and fix the bugs

import java.io.*;

public class Day58ErrorQuiz {

    public static void main(String[] args) {

        String filename = "output.txt"           // Bug 1 - missing semicolon

        try {
            FileWriter fw = new FileWriter(filename);
            fw.write("Hello, World!");
            // Bug 2 - fw.close() not called, resource leak. Should use try-with-resources

            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            System.out.println(line)             // Bug 3 - missing semicolon
            br.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}