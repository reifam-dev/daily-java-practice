import java.util.ArrayList;
import java.util.List;

class Participant {
    String name;
    boolean willVoteYes;

    Participant(String name, boolean willVoteYes) {
        this.name = name;
        this.willVoteYes = willVoteYes;
    }

    boolean prepare() {
        return willVoteYes;
    }

    void commit() {
        System.out.println(name + ": committed");
    }

    void abort() {
        System.out.println(name + ": aborted");
    }
}

public class Day162ErrorQuiz {
    private static boolean runTwoPhaseCommit(List<Participant> participants) {
        List<Boolean> votes = new ArrayList<>();
        for (Participant p : participants) {
            votes.add(p.prepare());
        }

        for (Participant p : participants) {
            p.commit()
        }

        return !votes.contains(false);
    }

    public static void main(String[] args) {
        List<Participant> participants = List.of(
                new Participant("investor-db", true),
                new Participant("deal-db", false)
        );
        System.out.println("Succeeded: " + runTwoPhaseCommit(participants));
    }
}