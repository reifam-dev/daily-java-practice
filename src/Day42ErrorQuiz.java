// Day 42 - Error Finding Quiz
// Find and fix the bugs

public class Day42ErrorQuiz {

    private String name;
    private boolean canFly;

    public Day42ErrorQuiz(String name, boolean canFly) {
        name = name;              // Bug 1 - missing this
        this.canFly = canFly;
    }

    public String getAbility() {
        if (canFly = true) {      // Bug 2 - assignment instead of comparison
            return name + " can fly.";
        }
        return name + " cannot fly.";
    }

    public static void main(String[] args) {
        Day42ErrorQuiz eagle = new Day42ErrorQuiz("Eagle", true)
        Day42ErrorQuiz fish = new Day42ErrorQuiz("Fish", false);  // Bug 3 - missing semicolon above
        System.out.println(eagle.getAbility());
        System.out.println(fish.getAbility());
    }

}