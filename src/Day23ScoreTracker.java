public class Day23ScoreTracker {

    public static void main(String[] args) {

        Player alice = new Player("Alice", 0);
        Player bob = new Player("Bob", 0);

        alice.addPoints(85);
        alice.addPoints(10);
        bob.addPoints(92);

        System.out.println(alice.getName() + " score: " + alice.getScore());
        System.out.println(bob.getName() + " score: " + bob.getScore());

        System.out.println("Alice is winning: " + (alice.getScore() > bob.getScore()));

        alice.reset();
        System.out.println("After reset, Alice score: " + alice.getScore());

    }

}

class Player {

    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        if (points > 0) {
            score += points;
        } else {
            System.out.println("Points must be positive.");
        }
    }

    public void reset() {
        score = 0;
    }

}