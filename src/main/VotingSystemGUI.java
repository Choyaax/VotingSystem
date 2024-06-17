package VotingSystem.src.main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class VotingSystemGUI extends JFrame {
    private JTextField voterNameField;
    private JTextField candidateField;
    private JTable votesTable;
    private VoteDAO voteDAO;

    public VotingSystemGUI() {
        voteDAO = new VoteDAO();
        initUI();
    }

    private void initUI() {
        setTitle("Voting System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        setContentPane(panel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Voter Name:"));
        voterNameField = new JTextField();
        inputPanel.add(voterNameField);

        inputPanel.add(new JLabel("Candidate:"));
        candidateField = new JTextField();
        inputPanel.add(candidateField);

        JButton addButton = new JButton("Add Vote");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVote();
            }
        });
        inputPanel.add(addButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshVotes();
            }
        });
        inputPanel.add(refreshButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        votesTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Voter Name", "Candidate"}, 0));
        JScrollPane scrollPane = new JScrollPane(votesTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete Vote");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteVote();
            }
        });
        panel.add(deleteButton, BorderLayout.SOUTH);

        refreshVotes(); 
    }

    private void addVote() {
        String voterName = voterNameField.getText();
        String candidate = candidateField.getText();
        if (!voterName.isEmpty() && !candidate.isEmpty()) {
            try {
                voteDAO.createVote(new Vote(voterName, candidate));
                refreshVotes(); 
                voterNameField.setText("");
                candidateField.setText("");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error adding vote: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        }
    }

    private void refreshVotes() {
        try {
            List<Vote> votes = voteDAO.readVotes();
            DefaultTableModel model = (DefaultTableModel) votesTable.getModel();
            model.setRowCount(0); 
            for (Vote vote : votes) {
                model.addRow(new Object[]{vote.getId(), vote.getVoterName(), vote.getCandidate()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading votes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void deleteVote() {
        int selectedRow = votesTable.getSelectedRow();
        if (selectedRow >= 0) {
            int voteId = (int) votesTable.getValueAt(selectedRow, 0);
            try {
                voteDAO.deleteVote(voteId);
                refreshVotes(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting vote: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a vote to delete.");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VotingSystemGUI votingSystemGUI = new VotingSystemGUI();
                votingSystemGUI.setVisible(true);
            }
        });
    }
}
