package VotingSystem.src.main.java;

import java.util.List;

public class VotingSystem {
    public static void main(String[] args) {
        CandidateDAO dao = new CandidateDAO();

   
        List<Candidate> candidates = dao.getAllCandidates();
        System.out.println("Candidates:");
        for (Candidate candidate : candidates) {
            System.out.println(candidate.getId() + ": " + candidate.getName() + " - Votes: " + candidate.getVotes());
        }

     
        dao.addVote("Candidate A");

     
        System.out.println("\nUpdated Candidates:");
        candidates = dao.getAllCandidates();
        for (Candidate candidate : candidates) {
            System.out.println(candidate.getId() + ": " + candidate.getName() + " - Votes: " + candidate.getVotes());
        }
    }
}
