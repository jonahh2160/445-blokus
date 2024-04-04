
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class GUI extends JFrame {

    private JPanel boardPanel;
    private JPanel[][] board;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private JLabel player1Score;
    private JLabel player2Score;
    private int boardSize = 20;

    public GUI() {
        setTitle("Blokus Game");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createBoardPanel();
        createPlayer1Panel();
        createPlayer2Panel();
        createScoreLabels();

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(player1Panel, BorderLayout.CENTER);
        leftPanel.add(player1Score, BorderLayout.NORTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(player2Panel, BorderLayout.CENTER);
        rightPanel.add(player2Score, BorderLayout.NORTH);


        add(boardPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void createBoardPanel() {
        boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
        board = new JPanel[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new JPanel();
                board[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                boardPanel.add(board[i][j]);
            }
        }
    }

    private void createPlayer1Panel() {
        player1Panel = new JPanel();
        player1Panel.setLayout(new BorderLayout());

        JLabel player1Label = new JLabel("Player 1");
        player1Panel.add(player1Label, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(new JPanel());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(200, getHeight()));
        player1Panel.add(scroll, BorderLayout.CENTER);
    }

    private void createPlayer2Panel() {
        player2Panel = new JPanel();
        player2Panel.setLayout(new BorderLayout());

        JLabel player2Label = new JLabel("Player 2");
        player2Panel.add(player2Label, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(new JPanel());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(200, getHeight()));
        player2Panel.add(scroll, BorderLayout.CENTER);
    }

    private void createScoreLabels() {
        player1Score = new JLabel("Score: 0");
        player2Score = new JLabel("Score: 0");
    }
}