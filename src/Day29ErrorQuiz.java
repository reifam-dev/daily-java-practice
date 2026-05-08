// Day 29 - Error Finding Quiz
// Find and fix the bugs

public class Day29ErrorQuiz {

    private String playerName;
    private String teamName;
    private boolean active;

    public Day29ErrorQuiz(String playerName, String teamName) {
        playerName = playerName;       // Bug 1 - missing this
        this.teamName = teamName;
        active = true;
    }

    public void retire() {
        active == false;               // Bug 2 - comparison not assignment
    }

    public boolean isActive() {
        return active;
    }

    public String getPlayerName() {
        return playerName;
    }

    public static void main(String[] args) {
        Day29ErrorQuiz player = new Day29ErrorQuiz("Alice", "Eagles")
        System.out.println(player.getPlayerName());  // Bug 3 - missing semicolon above
        player.retire();
        System.out.println(player.isActive());
    }

}