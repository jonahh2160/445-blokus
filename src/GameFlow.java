public class GameFlow {
    Boolean gameOver = false;
    GameBoard gameBoard;
    GameLogic logic; 
    //May not need numberOfPieces if keep track of endgame through boolean condition or something else
    int numberOfPieces = 21;
    PieceColor color;
    PieceColor red;
    PieceColor blue;
    PieceColor green;
    PieceColor yellow;
    Piece piece;
    GUI clicked; 

    void createSinglePlayerGame(GameBoard gameBoard) {


        for(int i = 0; i == 1; i++){
            firstPlayerTurn(red, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            firstPlayerTurn(blue, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            firstPlayerTurn(green, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            firstPlayerTurn(yellow, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
        }

        while (!gameOver) {
            playerTurn(red, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(blue, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(green, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(yellow, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            endGameCheck();

            // player1Turn(player, numberOfPieces);
            // cpuTurn(cpu);
        }

    }

    //void createTwoPlayerGame(GameBoard gameBoard, PieceColor red, PieceColor blue,
            //PieceColor green, PieceColor yellow,int numberOfPieces) {
                //Place first move check up front instead of calling firstPlayerTurn

        for(int i = 0; i == 1; i++){
            firstPlayerTurn(red, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            firstPlayerTurn(blue, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            firstPlayerTurn(green, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            firstPlayerTurn(yellow, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
                }

        while (!gameOver) {

            playerTurn(red, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(blue, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(green, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(yellow, piece, numberOfPieces, gameBoard, clicked.getX(), clicked.getY());
            endGameCheck();

            // player1Turn(player1, numberOfPieces);
            // player2Turn(player2, numberOfPieces);

        }
    }

    // MT: trying to work through logic of player turn
    void playerTurn(PieceColor color, Piece piece, int numberOfPieces, GameBoard gameBoard, int xCoordinate, int yCoordinate) {
        //Selects piece to play
        logic.pieceSelect(piece);
        //Places piece on board
        gameBoard.placePiece(piece, xCoordinate, yCoordinate);
        //Maybe don't even need this if use noMove boolean to trigger end of game? 
        numberOfPieces -= numberOfPieces;
        

    }

    //MT: Turn method that is executed for all four colors in the initial loop to check for legal first moves
    void firstPlayerTurn(PieceColor color, Piece piece, int numberOfPiecesj, GameBoard gameBoard, int xCoordinate, int yCoordinate){
           //Selects piece to play
           logic.pieceSelect(piece);
           //Places piece on board
           gameBoard.placeFirstPiece(piece, xCoordinate, yCoordinate);
           //Maybe don't even need this if use noMove boolean to trigger end of game? 
           numberOfPieces -= numberOfPieces;
    }

    //Mt maybe set noMoves boolean that gets triggered by skip button click or something and after every gameloop call to check all colors noMoves status
    //IF all statuses are set to true for noMoves then the endGame() function is called
    void endGameCheck(){
        //Idea is to have a boolean set on color/player for noMoves that is initially set to false
        //Create a noMoves check method that calls getters for that variable and when all are set to true 
        //We call the endGame() and calc scores
        endGame();

    }

    void endGame() {
        // Logic for player turn;
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
