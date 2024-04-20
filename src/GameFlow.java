// MT 
public class GameFlow {
    Boolean gameOver = false;
    GameBoard gameBoard;
    GameLogic logic; 
    PieceColor blue = PieceColor.BLUE;
    PieceColor yellow = PieceColor.YELLOW;
    PieceColor red = PieceColor.RED;
    PieceColor green = PieceColor.GREEN;
    Piece[] invBlue, invRed, invGreen, invYellow;
    Piece piece;
    Piece lastPieceRed;
    Piece lastPieceBlue;
    Piece lastPieceGreen;
    Piece lastPieceYellow;
    GUI clicked; 

    void createSinglePlayerGame(GameBoard gameBoard) {

        invBlue = gameBoard.createInvPieces(blue);
        invGreen = gameBoard.createInvPieces(green);
        invRed = gameBoard.createInvPieces(red);
        invYellow = gameBoard.createInvPieces(yellow);

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
        setLastPieceAs(color, piece);
        //Selects piece to play
        logic.pieceSelect(piece);
        //Places piece on board
        gameBoard.placePiece(piece, xCoordinate, yCoordinate);
        
    }

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
        chooseWinner(lastPieceRed, lastPieceBlue, lastPieceGreen, lastPieceYellow);
    }

    // JH: Runs GameBoard's calculate score method to declare a winner
    void chooseWinner(Piece lastBlue, Piece lastYellow, Piece lastRed, Piece lastGreen) {
        int scoreP1 = gameBoard.calcScoreP1(lastBlue, lastRed);
        int scoreP2 = gameBoard.calcScoreP2(lastYellow, lastGreen);

        if (scoreP1 > scoreP2) {
            // TODO: P1 wins logic
        } else if (scoreP1 == scoreP2) {
            // TODO: Draw logic
        } else {
            // TODO: P2 wins logic
        }
    }
}
