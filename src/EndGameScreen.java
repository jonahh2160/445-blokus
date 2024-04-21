//MT 4/20/24
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameScreen implements ActionListener{
    private final GUI blokusGUI;
    
    public EndGameScreen(int scoreP1, int scoreP2, GUI blokusGUI) {
        this.blokusGUI = blokusGUI;
        final String p1 ="Player 1";
        final String p2 = "Player 2";
    
        JFrame frame = new JFrame();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Final Score!");
    
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(panel);
        frame.setLocationRelativeTo(null);

        JLabel declareTheWinner;
        if(scoreP1 > scoreP2){
            declareTheWinner = new JLabel("THE WINNER IS " + p1 + "!!!!!!!!!!!!!!!!!!!!!!!");
        } else if( scoreP1 < scoreP2){
            declareTheWinner = new JLabel("THE WINNER IS " + p2 + "!!!!!!!!!!!!!!!!!!!!!!!");
    
        } else{
            declareTheWinner = new JLabel("EVERYONE WINS!!!!!!!!!!!!!!!");
        }
        JLabel player1Score = new JLabel("Player 1: " + scoreP1);
        JLabel player2Score = new JLabel("Player 2: " + scoreP2);
        panel.add(declareTheWinner, gbc);
        panel.add(player1Score, gbc);
        panel.add(player2Score, gbc);
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton playAgainButton = new JButton("Play Again!");
        playAgainButton.setPreferredSize(new Dimension(300, 60));
        playAgainButton.setBackground(Color.GREEN);
        playAgainButton.addActionListener(this);
        buttonPanel.add(playAgainButton);
        panel.add(playAgainButton, gbc);
    
        JPanel quitButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton quitPlayingButton = new JButton(" Quit Playing :( ");
        quitPlayingButton.setPreferredSize(new Dimension(300, 60));
        quitPlayingButton.setBackground(Color.RED);
        quitPlayingButton.addActionListener(this);
        quitButtonPanel.add(quitPlayingButton);
        panel.add(quitPlayingButton, gbc);
    
        frame.setVisible(true);
    
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Play Again!")) {
                // Code to recall Main Screen I think
                new MainScreen(blokusGUI);
            } else if (e.getActionCommand().equals(" Quit Playing! ")) {
                // Code to close the application
                System.exit(0);
               
            }
        }
}
