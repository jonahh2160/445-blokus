import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Elijah Wheat 3/24/24
public class GUI extends JFrame implements MouseListener {

    private JPanel boardPanel;
    private GameBoard gameBoard;
    private GameLogic gameLogic;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private JLabel player1Score;
    private JLabel player2Score;



    public GUI() {
        gameBoard = new GameBoard();
        gameLogic = new GameLogic();


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

        // Add mouse listener to each button on the board
        for(Component comp : boardPanel.getComponents()) {
            if(comp instanceof JButton button) {
                button.addMouseListener(this);
            }
        }

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
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e ->{
                    //call method to play piece and need to add if statement to check valid move

                    //update button color
                    button.setBackground(getSpaceColor(gameBoard.getSpaceValue(finalI, finalJ)));
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
        player1Score = new JLabel("Score: 0");
        player2Score = new JLabel("Score: 0");
    }

    // MouseListener methods
    @Override
    public void mouseClicked(MouseEvent e) {
        //handle mouse click event
        JButton clickedButton = (JButton)e.getSource();

        //get the index of the clicked button in the boardPanel
        int index = boardPanel.getComponentZOrder(clickedButton);

        //calculate row and column based on the index
        int row = index / 20;
        int col = index % 20;

        //get top left button
        int topLeftRow = Math.max(row - 1, 0);
        int topLeftCol = Math.max(col - 1, 0);

        //print the row and column
        System.out.println("Clicked Button Location: Row " + row + ", Column " + col);
        //print top left button
        System.out.println("Top-left Button Location: Row " + topLeftRow + ", Colum " + topLeftCol);
    }

    // Other MouseListener methods
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}