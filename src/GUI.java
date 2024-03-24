
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
public class GUI extends JFrame{
    private JButton startButton;
    private JComboBox<Integer> playerSelection;
    private JPanel boardPanel;
    private JPanel[][] board;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private JLabel player1Score;
    private JLabel player2Score;
    private int playerNumber;
    private boolean gameStarted;
    private int boardSize = 20;

    public GUI() {
        setTitle("Blokus Game");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createPlayerSelection();
        createStartButton();
        createBoardPanel();
        createPlayer1Panel();
        createPlayer2Panel();
        createScoreLabels();

        boardPanel.setVisible(false);
        player1Panel.setVisible(false);
        player2Panel.setVisible(false);
        player1Score.setVisible(false);
        player2Score.setVisible(false);

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(playerSelection, BorderLayout.NORTH);
        controlPanel.add(startButton, BorderLayout.SOUTH);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(player1Panel, BorderLayout.CENTER);
        leftPanel.add(player1Score, BorderLayout.NORTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(player2Panel, BorderLayout.CENTER);
        rightPanel.add(player2Score, BorderLayout.NORTH);

        add(leftPanel, BorderLayout.WEST);
        add(boardPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(controlPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void createPlayerSelection() {
        Integer[] playerOptions = {1, 2};

        playerSelection = new JComboBox<>(playerOptions);

        playerSelection.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                playerNumber = (Integer) playerSelection.getSelectedItem();
            }
        });
    }

    private void createStartButton() {
        startButton = new JButton("Start Game");

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
    }

    private void createBoardPanel() {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        board = new JPanel[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
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
        add(player1Panel, BorderLayout.WEST);
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
        add(player2Panel, BorderLayout.EAST);
    }

    private void createScoreLabels() {
        player1Score = new JLabel("Score: 0");
        player2Score = new JLabel("Score: 0");
    }

    private void startGame() {
        if (playerNumber > 0) {
            gameStarted = true;
            JOptionPane.showMessageDialog(this, "Game Started with " + playerNumber + " players.");

            boardPanel.setVisible(true);
            player1Panel.setVisible(true);
            player2Panel.setVisible(true);
            player1Score.setVisible(true);
            player2Score.setVisible(true);

            playerSelection.setVisible(false);
            startButton.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Please select number of players.");
        }
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}

