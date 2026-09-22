import java.util.List;

final class Participant {
    private final String name;
    private final boolean willVoteYes;

    public Participant(String name, boolean willVoteYes) {
        this.name = name;
        this.willVoteYes = willVoteYes;
    }

    public boolean prepare() {
        return this.willVoteYes;
    }

    public void commit() {
        System.out.println(this.name + ": committed");
    }

    public void abort() {
        System.out.println(this.name + ": aborted");
    }
}

/**
 * Phase 1 (prepare) must be unanimous before phase 2 (commit) begins
 * on anyone; a single "no" vote aborts every participant, not just
 * the dissenter.
 */
public class Day162TwoPhaseCommit {
    private static boolean runTwoPhaseCommit(List<Participant> participants) {
        boolean allYes = true;
        for (Participant p : participants) {
            if (!p.prepare()) {
                allYes = false;
            }
        }

        for (Participant p : participants) {
            if (allYes) {
                p.commit();
            } else {
                p.abort();
            }
        }

        return allYes;
    }

    public static void main(String[] args) {
        List<Participant> participants = List.of(
                new Participant("investor-db", true),
                new Participant("deal-db", false)
        );
        System.out.println("Succeeded: " + runTwoPhaseCommit(participants));
    }
}