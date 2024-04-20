import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

//Elijah Wheat 3/24/24
public class GUI extends JFrame implements MouseListener {
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

        //Create panels
        createBoardPanel();
        createPlayerPanels();
        createScoreLabels();

        //Set up the main frame
        add(player1Panel, BorderLayout.WEST);
        add(boardPanel, BorderLayout.CENTER);
        add(player2Panel, BorderLayout.EAST);

        setVisible(true);
    }

    //Method to create the game board panel
    private void createBoardPanel() {
        boardPanel = new JPanel(new GridLayout(20, 20));
        boardPanel.setPreferredSize(new Dimension(600, 600));

        //Create buttons for the game board
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(30, 30));
                button.setBackground(getSpaceColor(gameBoard.getSpaceValue(i, j)));
                button.addMouseListener(this);
                boardPanel.add(button);
            }
        }
    }

    //Method to create player panels
    private void createPlayerPanels() {
        //Create the player 1 panel
        player1Panel = new JPanel(new BorderLayout());
        JScrollPane player1PiecesPanel = createPiecesPanel(PieceColor.BLUE, PieceColor.RED);
        player1Panel.add(player1PiecesPanel, BorderLayout.CENTER);
        player1Score = new JLabel("Player 1 Score: 0");
        player1Panel.add(player1Score, BorderLayout.NORTH);

        //Create the player 2 panel
        player2Panel = new JPanel(new BorderLayout());
        JScrollPane player2PiecesPanel = createPiecesPanel(PieceColor.YELLOW, PieceColor.GREEN);
        player2Panel.add(player2PiecesPanel, BorderLayout.CENTER);
        player2Score = new JLabel("Player 2 Score: 0");
        player2Panel.add(player2Score, BorderLayout.NORTH);
    }

    //Method to create a panel with pieces for a player
    private JScrollPane createPiecesPanel(PieceColor color1, PieceColor color2) {
        JPanel piecesPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        Piece[] pieces1 = gameBoard.createInvPieces(color1);
        Piece[] pieces2 = gameBoard.createInvPieces(color2);

        //Add pieces to the panel
        addPiecesToPanel(piecesPanel, pieces1);
        addPiecesToPanel(piecesPanel, pieces2);

        //Wrap the panel in a scroll pane for better usability
        JScrollPane scrollPane = new JScrollPane(piecesPanel);
        scrollPane.setPreferredSize(new Dimension(200, 800));
        return scrollPane;
    }

    //Method to add pieces to a panel
    private void addPiecesToPanel(JPanel panel, Piece[] pieces) {
        for (Piece piece : pieces) {
            // Create a new button for each piece
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(60, 60));

            //Set the icon of the button to represent the piece
            button.setIcon(createPieceIcon(piece));

            //Set the background color of the button according to the piece's color
            Color pieceColor = getPieceColor(piece.getColor());
            //button.setBackground(pieceColor);

            //Add the button to the panel
            panel.add(button);
        }
    }

    //Method to create an icon for a piece
    private Icon createPieceIcon(Piece piece) {
        //Define the size of the icon (e.g., adjust as needed)
        int iconSize = 30;

        //Create a BufferedImage
        BufferedImage image = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        //Get the shape of the piece
        int[][] shape = getPieceShape(piece);
        //Get the color of the piece
        Color pieceColor = getPieceColor(piece.getColor());

        //Calculate cell width and height based on the shape dimensions
        int cellWidth = iconSize / shape[0].length;
        int cellHeight = iconSize / shape.length;

        //Fill the BufferedImage based on the shape and color of the piece
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] == 1 || shape[row][col] == 2 || shape[row][col] == 3 || shape[row][col] == 4) {
                    graphics.setColor(pieceColor);
                    //Draw the colored cell in the BufferedImage
                    graphics.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                }
            }
        }

        //Dispose of graphics context
        graphics.dispose();

        //Convert the BufferedImage to an ImageIcon and return it
        return new ImageIcon(image);
    }

    //Method to create the shape of a piece as a 2D array
    private int[][] getPieceShape(Piece piece) {
        int width = piece.getWidth();
        int height = piece.getHeight();

        //Create a 2D array to hold the shape of the piece
        int[][] shape = new int[height][width];

        //Fill the 2D array using the piece's coordinates
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //Use the getCoordValue() method to get the value at each coordinate
                shape[y][x] = piece.getCoordValue(x, y);
            }
        }

        return shape;
    }

    //Method to get the color for a piece
    private Color getPieceColor(int color) {
        return switch (color) {
            case 1 -> Color.BLUE;
            case 2 -> Color.RED;
            case 3 -> Color.YELLOW;
            case 4 -> Color.GREEN;
            default -> Color.WHITE;
        };
    }

    //Method to create score labels
    private void createScoreLabels() {
        player1Score = new JLabel("Player 1 Score: 0");
        player2Score = new JLabel("Player 2 Score: 0");

        player1Panel.add(player1Score, BorderLayout.NORTH);
        player2Panel.add(player2Score, BorderLayout.NORTH);
    }

    //MouseListener methods
    @Override
    public void mouseClicked(MouseEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        int index = boardPanel.getComponentZOrder(clickedButton);

        //Calculate row and column based on the index
        int row = index / 20;
        int col = index % 20;

        //Get top left button
        int topLeftRow = row - 1;
        int topLeftCol = col - 1;

        //Handle the button click event
        //For example, place a piece on the board and update the button background color
        gameBoard.placePiece(new Piece(Piece.Type.ONE, PieceColor.BLUE), col, row);
        clickedButton.setBackground(getSpaceColor(gameBoard.getSpaceValue(row, col)));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //Method to get the color for a space
    private Color getSpaceColor(int spaceValue) {
        return switch (spaceValue) {
            case 0 -> Color.WHITE;
            case 1 -> Color.BLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.RED;
            case 4 -> Color.GREEN;
            default -> Color.WHITE;
        };
    }
}