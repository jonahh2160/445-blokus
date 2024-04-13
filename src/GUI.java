import javax.swing.*;
import java.awt.*;

//Elijah Wheat 3/24/24
public class GUI extends JFrame {

    private JPanel boardPanel;
    private GameBoard gameBoard;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private JLabel player1Score;
    private JLabel player2Score;


    public GUI() {
        gameBoard = new GameBoard();


        setTitle("Blokus Game");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        //create panels for game board
        createBoardPanel();
        createPlayer1Panel();
        createPlayer2Panel();
        createScoreLabels();

        //player 1 panel with score
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(player1Panel, BorderLayout.CENTER);
        leftPanel.add(player1Score, BorderLayout.NORTH);

        //player 2 panel with score
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(player2Panel, BorderLayout.CENTER);
        rightPanel.add(player2Score, BorderLayout.NORTH);

        //add the game board, player panels, and score labels to main frame
        add(boardPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        setVisible(true);
    }

    //method to create game board panel
    private void createBoardPanel() {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(20,20));

        //used JButtons to represent the space on the board
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(30,30));
                button.setBackground(getSpaceColor(gameBoard.getSpaceValue(i,j)));
                button.addActionListener(e ->{
                    //call method to play piece
                    //update button color

                });
                boardPanel.add(button);
            }
        }
    }

    //Method to get the color for a space
    private Color getSpaceColor(int spaceValue){
        return switch (spaceValue) {
            case 0 -> Color.WHITE;
            case 1 -> Color.BLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.RED;
            case 4 -> Color.GREEN;
            default -> Color.WHITE;
        };
    }

    //method to create the panel for player 1
    private void createPlayer1Panel() {
        player1Panel = new JPanel();
        player1Panel.setLayout(new BorderLayout());

        JLabel player1Label = new JLabel("Player 1");
        player1Panel.add(player1Label, BorderLayout.NORTH);

        //add pieces to player1 panel

        JScrollPane scroll = new JScrollPane(new JPanel());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(200, getHeight()));
        player1Panel.add(scroll, BorderLayout.CENTER);
    }

    //method to create the panel for player 2
    private void createPlayer2Panel() {
        player2Panel = new JPanel();
        player2Panel.setLayout(new BorderLayout());

        JLabel player2Label = new JLabel("Player 2");
        player2Panel.add(player2Label, BorderLayout.NORTH);

        //add pieces to player2 panel

        JScrollPane scroll = new JScrollPane(new JPanel());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(200, getHeight()));
        player2Panel.add(scroll, BorderLayout.CENTER);
    }

    //method to display score
    private void createScoreLabels() {

    }
}