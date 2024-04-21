// MT 
public class GameFlow {
    private GUI clicked;
    private GUI blokusGUI;
    private GameBoard gameBoard;
    private GameLogic logic;
    private Boolean gameOver = false;
    private PieceColor blue = PieceColor.BLUE;
    private PieceColor yellow = PieceColor.YELLOW;
    private PieceColor red = PieceColor.RED;
    private PieceColor green = PieceColor.GREEN;
    private Piece piece;
    private Piece lastPieceRed;
    private Piece lastPieceBlue;
    private Piece lastPieceGreen;
    private Piece lastPieceYellow;
    Piece[] invBlue, invRed, invGreen, invYellow;
    EndGameScreen endGameScreen;


    void createSinglePlayerGame(GameBoard gameBoard) {

        invBlue = gameBoard.createInvPieces(blue);
        invGreen = gameBoard.createInvPieces(green);
        invRed = gameBoard.createInvPieces(red);
        invYellow = gameBoard.createInvPieces(yellow);

        if (clicked == null) {
            System.out.println("Error: clicked is null in createOnePlayerGame.");
            return; // Exit the method early
        }

        for(int i = 0; i == 1; i++){
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(red, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(blue, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(green, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(yellow, piece, gameBoard, clicked.getX(), clicked.getY());
        }

        while (!gameOver) {
            playerTurn(red, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(blue, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(green, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(yellow, piece, gameBoard, clicked.getX(), clicked.getY());
            endGameCheck();
        }

    }

    void createTwoPlayerGame(GameBoard gameBoard) {
        invBlue = gameBoard.createInvPieces(blue);
        invGreen = gameBoard.createInvPieces(green);
        invRed = gameBoard.createInvPieces(red);
        invYellow = gameBoard.createInvPieces(yellow);

        if (clicked == null) {
            System.out.println("Error: clicked is null in createTwoPlayerGame.");
            return; // Exit the method early
        }

        for(int i = 0; i == 1; i++){
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(red, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(blue, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(green, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(yellow, piece, gameBoard, clicked.getX(), clicked.getY());
        }

        while (!gameOver) {

            playerTurn(red, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(blue, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(green, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(yellow, piece, gameBoard, clicked.getX(), clicked.getY());
            endGameCheck();

        }
    }

    // MT: trying to work through logic of player turn
    void playerTurn(PieceColor color, Piece piece, GameBoard gameBoard, int xCoordinate, int yCoordinate) {
        //Here we'll place Jonah's method to check for available moves first
        //Selects piece to play
        logic.pieceSelect(piece);
        //Places piece on board
        gameBoard.placePiece(piece, xCoordinate, yCoordinate);
        //Method to keep track of last piece for each color
        setLastPieceAs(color, piece);

    }

    //MT method to update the last piece placed for each color
    void setLastPieceAs(PieceColor color, Piece piece){
        if(color == red){
            lastPieceRed = piece;
        } else if(color == blue){
            lastPieceBlue = piece;
        } else if(color == green){
            lastPieceGreen = piece;
        } else {
            lastPieceYellow = piece;
        }
    }

    //Mt Still need to configure for what Jonah's method is returning to make gameOverSignifier equivalent to it
    void endGameCheck(){
        //MT here we'll check whatever Jonah's method return and depending on it returns execute logic
        String gameOverSignifier = " ";
        //gameOverSignifier = logic.findMove();
        if( gameOverSignifier == null){
            endGame();
        }

    }

    void endGame() {
        gameOver = true;
        chooseWinner(lastPieceRed, lastPieceBlue, lastPieceGreen, lastPieceYellow, blokusGUI);
    }

    // JH: Runs GameBoard's calculate score method to declare a winner
    void chooseWinner(Piece lastBlue, Piece lastYellow, Piece lastRed, Piece lastGreen, GUI blokusGUI) {
        int scoreP1 = gameBoard.calcScoreP1(lastBlue, lastRed);
        int scoreP2 = gameBoard.calcScoreP2(lastYellow, lastGreen);
        //MT creates new End Game Screen, handles different scenarios of scores itself
        EndGameScreen endGameScreen = new EndGameScreen(scoreP1, scoreP2, blokusGUI);
        endGameScreen.setVisible(true);

    }
}
