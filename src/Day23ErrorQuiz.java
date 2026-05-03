// Day 23 - Error Finding Quiz
// Find and fix the bugs

public class Day23ErrorQuiz {

    private String playerName;
    private int score;

    public Day23ErrorQuiz(String playerName, int score) {
        playerName = playerName;   // Bug 1 - missing this
        this.score = score;
    }

    public void addPoints(int points) {
        score =+ points;           // Bug 2 - wrong operator
    }

    public int getScore() {
        return score;
    }

    public static void main(String[] args) {
        Day23ErrorQuiz player = new Day23ErrorQuiz("Alice", 50)
        player.addPoints(20);      // Bug 3 - missing semicolon above
        System.out.println(player.getScore());
    }

}