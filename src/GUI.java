
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GUI extends JFrame implements MouseListener, MouseMotionListener{
    private JPanel boardPanel;
    private GameBoard gameBoard;
    private GameLogic gameLogic;
    private GameFlow gameFlow;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private JLabel player1Score;
    private JLabel player2Score;
    private Piece selectedPiece;
    private boolean isFirstTurn = true;
    private JButton[][] boardButtons;


    public GUI() {
        gameBoard = new GameBoard();
        gameLogic = new GameLogic();
        this.gameFlow = new GameFlow();

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

        boardButtons = new JButton[20][20];

        //Create buttons for the game board
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(30, 30));
                int spaceValue = gameBoard.getSpaceValue(i, j);
                button.setBackground(getPieceColor(spaceValue));
                button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                button.addMouseListener(this);
                button.addMouseMotionListener(this);
                boardPanel.add(button);
                boardButtons[i][j] = button;
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
        
        //Add buttons for rotation
        JPanel player1ButtonPanel = new JPanel();
        JButton rotateRightButton1 = new JButton("Rotate Right");
        JButton rotateLeftButton1 = new JButton("Rotate Left");
        rotateRightButton1.addActionListener(e -> rotateSelectedPieceRight());
        rotateLeftButton1.addActionListener(e -> rotateSelectedPieceLeft());
        player1ButtonPanel.add(rotateRightButton1);
        player1ButtonPanel.add(rotateLeftButton1);
        player1Panel.add(player1ButtonPanel, BorderLayout.SOUTH);

        //Create the player 2 panel
        player2Panel = new JPanel(new BorderLayout());
        JScrollPane player2PiecesPanel = createPiecesPanel(PieceColor.YELLOW, PieceColor.GREEN);
        player2Panel.add(player2PiecesPanel, BorderLayout.CENTER);
        player2Score = new JLabel("Player 2 Score: 0");
        player2Panel.add(player2Score, BorderLayout.NORTH);
        
        //Add buttons for rotation
        JPanel player2ButtonPanel = new JPanel();
        JButton rotateRightButton2 = new JButton("Rotate Right");
        JButton rotateLeftButton2 = new JButton("Rotate Left");
        rotateRightButton2.addActionListener(e -> rotateSelectedPieceRight());
        rotateLeftButton2.addActionListener(e -> rotateSelectedPieceLeft());
        player2ButtonPanel.add(rotateRightButton2);
        player2ButtonPanel.add(rotateLeftButton2);
        player2Panel.add(player2ButtonPanel, BorderLayout.SOUTH);
    }

    //Method to create a panel with pieces for a player
    private JScrollPane createPiecesPanel(PieceColor color1, PieceColor color2) {
        JPanel piecesPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        Piece[] pieces1 = gameBoard.createInvPieces(color1);
        Piece[] pieces2 = gameBoard.createInvPieces(color2);

        addPiecesToPanel(piecesPanel, pieces1);
        addPiecesToPanel(piecesPanel, pieces2);

        JScrollPane scrollPane = new JScrollPane(piecesPanel);
        scrollPane.setPreferredSize(new Dimension(200, 800));
        return scrollPane;
    }

    //Method to add pieces to a panel
    private void addPiecesToPanel(JPanel panel, Piece[] pieces) {
        for (Piece piece : pieces) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(60, 60));
            button.setIcon(createPieceIcon(piece));
            button.addActionListener(e -> {
                gameLogic.pieceSelect(piece);
                selectedPiece = piece; 
                gameFlow.setCurrentInventory(pieces);
            });
            panel.add(button);
        }
    } 

    //Method to create an icon for a piece
    private Icon createPieceIcon(Piece piece) {
        int iconSize = 30;

        //Create a BufferedImage
        BufferedImage image = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        int[][] shape = getPieceShape(piece);
        Color pieceColor = getPieceColor(piece.getColor());

        //Calculate cell width and height based on the shape dimensions
        int cellWidth = iconSize / shape[0].length;
        int cellHeight = iconSize / shape.length;

        Color outlineColor = Color.BLACK;

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    graphics.setColor(pieceColor);
                    graphics.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                    graphics.setColor(outlineColor);
                    graphics.drawRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                }
            }
        }
        graphics.dispose();
        return new ImageIcon(image);
    }

    //Method to create the shape of a piece as a 2D array
    private int[][] getPieceShape(Piece piece) {
        int width = piece.getWidth();
        int height = piece.getHeight();
        int[][] shape = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
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

    private void updateScoreLabels() {
        //Calculate scores
        int scoreP1 = gameBoard.calcScoreP1(selectedPiece, selectedPiece);
        int scoreP2 = gameBoard.calcScoreP2(selectedPiece, selectedPiece);
        if(selectedPiece.getColor() == 1 || selectedPiece.getColor() == 2) {
        	player1Score.setText("Player 1 Score: " + scoreP1);
        }else if(selectedPiece.getColor() == 3 || selectedPiece.getColor() == 4){
        	player2Score.setText("Player 2 Score: " + scoreP2);
        }
    }
    
    //Method to rotate the selected piece to the right
    private void rotateSelectedPieceRight() {
        if (selectedPiece != null) {
            selectedPiece.rotateRight();
        }
    }
    
    //Method to rotate the selected piece to the left
    private void rotateSelectedPieceLeft() {
        if (selectedPiece != null) {
            selectedPiece.rotateLeft();
        }
    }
    
    //MT added trying to work through png's
    private Icon createBlockImage(Piece peice, Color color){
        int imageSize = 40;
        String imagePath= "";

        if(color == Color.BLUE){
            imagePath = "assets/blocks1.png";
        }else if(color == Color.YELLOW){
            imagePath = "assets/blocks2.png";
        }else if(color == Color.RED){
            imagePath = "assets/blocks3.png";
        }else{
            imagePath = "assets/blocks4.png";
        }


        File imageFile = new File(imagePath);

        ImageIcon imageIcon = new ImageIcon(imageFile.getPath());

        Image scaledImage = imageIcon.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        return scaledIcon;

    }

    @Override
    //Implement the mouseMoved method from MouseMotionListener
    public void mouseMoved(MouseEvent e) {
        if (selectedPiece == null) {
            return;
        }

        JButton hoveredButton = (JButton) e.getSource();

        //Calculate the row and column of the hovered button based on its index in the board panel
        int index = boardPanel.getComponentZOrder(hoveredButton);
        int hoveredRow = index / 20;  
        int hoveredCol = index % 20; 
        
        clearHighlight();

        Color pieceColor = getPieceColor(selectedPiece.getColor());

        //Iterate through the selected piece's shape
        for (int y = 0; y < selectedPiece.getHeight(); y++) {
            for (int x = 0; x < selectedPiece.getWidth(); x++) {
                //Calculate the board row and column based on the piece's coordinates
                int boardRow = hoveredRow + y;
                int boardCol = hoveredCol + x;

                //Check if the placement is within bounds
                if (boardRow >= 0 && boardRow < 20 && boardCol >= 0 && boardCol < 20) {
                    //Check if the piece should be placed at this position
                    if (selectedPiece.getCoordValue(x, y) != 0) {
                        //Get the button at this position on the board
                        JButton buttonToHighlight = boardButtons[boardRow][boardCol];

                        //Set the border of the button to the piece color
                        buttonToHighlight.setBorder(BorderFactory.createLineBorder(pieceColor, 2));
                    }
                }
            }
        }
    }

    //Method to clear the previous highlight on the board
    private void clearHighlight() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                JButton button = boardButtons[i][j];
                button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Ensure a piece is selected before proceeding
        if (selectedPiece == null) {
            return;
        }
        
        if (gameFlow == null) {
            System.err.println("GameFlow is not initialized.");
            return; 
        }

        //Get the clicked button
        JButton clickedButton = (JButton) e.getSource();

        //Calculate the row and column of the clicked button based on its index in the board panel
        int index = boardPanel.getComponentZOrder(clickedButton);
        int clickedRow = index / 20; //Number of columns on the board
        int clickedCol = index % 20; //Number of rows on the board

        //Clear the previous highlight
        clearHighlight();

        
            if (gameLogic.isValidMove(selectedPiece, clickedRow, clickedCol)) {
                gameBoard.placePiece(selectedPiece, clickedRow, clickedCol);
                //gameFlow.getCurrentInventory().remove(selectedPiece);
                updateScoreLabels();
                updateBoardVisuals(clickedRow, clickedCol);
                gameFlow.switchPlayer();
            }
            selectedPiece = null;
    }

    //Add a method to update the board's visual representation
    private void updateBoardVisuals(int clickedRow, int clickedCol) {
        int pieceWidth = selectedPiece.getWidth();
        int pieceHeight = selectedPiece.getHeight();

        //Iterate through the selected piece's shape
        for (int y = 0; y < pieceHeight; y++) {
            for (int x = 0; x < pieceWidth; x++) {
                if (selectedPiece.getCoordValue(x, y) != 0) {
                    int boardRow = clickedRow + y;
                    int boardCol = clickedCol + x;

                    if (boardRow >= 0 && boardRow < 20 && boardCol >= 0 && boardCol < 20) {
                        //Calculate the button index in the board panel
                        int buttonIndex = boardRow * 20 + boardCol;

                        //Get the button at this index
                        JButton button = (JButton) boardPanel.getComponent(buttonIndex);

                        //Update the button's background color according to the selected piece's color
                        button.setBackground(getPieceColor(selectedPiece.getColor()));

                        //Set the icon of the button
                        button.setIcon(createBlockImage(selectedPiece, getPieceColor(selectedPiece.getColor())));
                    }
                }
            }
        }
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
        clearHighlight();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}