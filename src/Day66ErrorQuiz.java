// Day 66 - Error Finding Quiz
// Find and fix the bugs

import java.io.*;

public class Day66ErrorQuiz {

    private String filename;
    private BufferedWriter writer;

    public Day66ErrorQuiz(String filename) {
        filename = filename;          // Bug 1 - missing this
    }

    public void open() throws IOException {
        writer = new BufferedWriter(new FileWriter(filename));
    }

    public void write(String content) throws IOException {
        writer.write(content)         // Bug 2 - missing semicolon
        writer.newLine();
    }

    public void close() throws IOException {
        if (writer == null) {
            writer.close();           // Bug 3 - should check writer != null
        }
    }

    public static void main(String[] args) {
        Day66ErrorQuiz fw = new Day66ErrorQuiz("output.txt");
        try {
            fw.open();
            fw.write("Hello World");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}