// Day 11 - Simple Error Finding Quiz

public class Day11ErrorQuiz {
    public static void main(String[] args) {
        ListManager manager = new ListManager();
        manager.addItem("apple");
        manager.addItem("banana");
        System.out.println(manager.contains("apple"));
        System.out.println(manager.size());
    }
}