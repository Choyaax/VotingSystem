package VotingSystem.src.main.java;  // Adjusted package declaration based on your project structure

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {
    private Connection connection;

    public CandidateDAO() {
        try {
            this.connection = Database.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        String sql = "SELECT * FROM Candidates";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Candidate candidate = new Candidate(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("votes")
                );
                candidates.add(candidate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    public void addVote(String candidateName) {
        String sql = "UPDATE Candidates SET votes = votes + 1 WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, candidateName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCandidate(Candidate candidate) {
        String sql = "INSERT INTO Candidates (name, votes) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, candidate.getName());
            stmt.setInt(2, candidate.getVotes());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCandidate(Candidate candidate) {
        String sql = "UPDATE Candidates SET name = ?, votes = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, candidate.getName());
            stmt.setInt(2, candidate.getVotes());
            stmt.setInt(3, candidate.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCandidate(int id) {
        String sql = "DELETE FROM Candidates WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CandidateDAO dao = new CandidateDAO();
        List<Candidate> candidates = dao.getAllCandidates();
        for (Candidate candidate : candidates) {
            System.out.println(candidate.getName() + " - Votes: " + candidate.getVotes());
        }
    }
}
