public class Day29SportsTeam {

    public static void main(String[] args) {

        Player alice = new Player("Alice", "Eagles");
        Player bob = new Player("Bob", "Eagles");
        Player charlie = new Player("Charlie", "Eagles");

        System.out.println(alice.getPlayerName()
                + " - team: " + alice.getTeamName()
                + " - active: " + alice.isActive());

        bob.retire();
        System.out.println(bob.getPlayerName()
                + " active: " + bob.isActive());

        bob.rejoin("Falcons");
        System.out.println(bob.getPlayerName()
                + " rejoined as: " + bob.getTeamName()
                + " - active: " + bob.isActive());

        charlie.retire();
        charlie.retire();   // Should print warning - already retired

    }

}

class Player {

    private String playerName;
    private String teamName;
    private boolean active;

    public Player(String playerName, String teamName) {
        this.playerName = playerName;
        this.teamName = teamName;
        this.active = true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public boolean isActive() {
        return active;
    }

    public void retire() {
        if (!active) {
            System.out.println(playerName + " is already retired.");
        } else {
            active = false;
        }
    }

    public void rejoin(String newTeamName) {
        this.teamName = newTeamName;
        this.active = true;
    }

}
