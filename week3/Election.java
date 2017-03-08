package week3;

import java.util.*;

/**
 * Created by ongteckwu on 12/2/17.
 */
public class Election {
    public static void main(String[] args) {
        int electoralVotes = 5;
        Map<String, Candidate> candidates = new HashMap<>();
        candidates.put("A", new Candidate("A"));
        candidates.put("B", new Candidate("B"));
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < electoralVotes; i++) {
            while (true) {
                System.out.print(String.format("What's your vote electorate %d? > ", i + 1));
                String who = scanner.next();
                if (!candidates.containsKey(who)) {
                    System.out.println("Your vote stinks! Please try again!");
                } else {
                    Candidate c = candidates.get(who);
                    c.addVote();
                    break;
                }
            }
        }

        Candidate maxC = getMaxVote(candidates.values());
        System.out.println(String.format("Candidate %s has won the election with %d votes!", maxC.getName(), maxC.getVotes()));
    }

    private static Candidate getMaxVote(Collection<Candidate> candidates) {
        Candidate maxC = null;
        int maxVotes = 0;
        for (Candidate candidate : candidates) {
            if (candidate.getVotes() >= maxVotes) {
                maxC = candidate;
                maxVotes = maxC.getVotes();
            }
        }

        return maxC;
    }
}

class Candidate {
    private int votes;
    private String name;

    public Candidate(String name) {
        this.name = name;
    }

    public void addVote() {
        votes++;
    }

    public int getVotes() {
        return votes;
    }

    public String getName() {
        return name;
    }

}
