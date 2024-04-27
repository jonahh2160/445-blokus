import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Michael Toon 3/28/24



public class MainScreen extends JFrame implements ActionListener {

    private final GUI blokusGUI; // GUI object
    private GameFlow gameflow; // GameFlow object
    private GameBoard gameBoard; // GameBoard object

    // Constructor
    public MainScreen(GUI blokusGUI) {
        this.blokusGUI = blokusGUI;
        gameBoard = new GameBoard();
        gameflow = new GameFlow(gameBoard);

        // Create frame
        JFrame frame = new JFrame();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WELCOME TO BLOKUS!");

        // Create panel and layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(panel);
        frame.setLocationRelativeTo(null);

        // Add buttons
        JButton singlePlayerButton = new JButton("Start Single Player Game!");
        singlePlayerButton.setPreferredSize(new Dimension(300, 60));
        singlePlayerButton.setBackground(Color.GREEN);
        singlePlayerButton.addActionListener(this);
        panel.add(singlePlayerButton, gbc);

        JButton multiPlayerButton = new JButton("Start Multi-Player Game!");
        multiPlayerButton.setPreferredSize(new Dimension(300, 60));
        multiPlayerButton.setBackground(Color.RED);
        multiPlayerButton.addActionListener(this);
        panel.add(multiPlayerButton, gbc);

        // Set visibility
        blokusGUI.setVisible(false);
        frame.setVisible(true);
    }

    // Main method
    public static void main(String[] args) {
        GUI blokusGUI = new GUI();
        new MainScreen(blokusGUI);
    }

    // Event handler
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Start Single Player Game!")) {
            //Check for null and call Single Player Game
            if (gameflow != null && gameBoard != null) {
                gameflow.createSinglePlayerGame(gameBoard);
                System.out.println("Starting Single Player Game!");
            } else {
                System.out.println("GameFlow or GameBoard is not initialized!");
            }
        } else if (actionCommand.equals("Start Multi-Player Game!")) {
            //Check for null and call Multi-Player Game
            if (gameflow != null && gameBoard != null) {
                gameflow.createTwoPlayerGame(gameBoard);
                System.out.println("Starting Multi-Player Game!");
            } else {
                System.out.println("GameFlow or GameBoard is not initialized!");
            }
        }
        blokusGUI.setVisible(true);
    }
}
