package VotingSystem.src.main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import VotingSystem.src.main.java.Database;

public class VoteDAO {
    public void createVote(Vote vote) throws SQLException {
        String sql = "INSERT INTO votes (voter_name, candidate) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vote.getVoterName());
            statement.setString(2, vote.getCandidate());
            statement.executeUpdate();
        }
    }

    public List<Vote> readVotes() throws SQLException {
        List<Vote> votes = new ArrayList<>();
        String sql = "SELECT * FROM votes";
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Vote vote = new Vote(resultSet.getString("voter_name"), resultSet.getString("candidate"));
                vote.setId(resultSet.getInt("id"));
                votes.add(vote);
            }
        }
        return votes;
    }

    public void updateVote(Vote vote) throws SQLException {
        String sql = "UPDATE votes SET voter_name = ?, candidate = ? WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vote.getVoterName());
            statement.setString(2, vote.getCandidate());
            statement.setInt(3, vote.getId());
            statement.executeUpdate();
        }
    }

    public void deleteVote(int id) throws SQLException {
        String sql = "DELETE FROM votes WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }  
}
