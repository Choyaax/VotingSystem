package VotingSystem.src.main;

public class Vote {
    private int id;
    private String voterName;
    private String candidate; 
    
    public Vote(String voterName, String candidate) {
        this.voterName = voterName;
        this.candidate = candidate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
}
