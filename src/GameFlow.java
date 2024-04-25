// MT 

public class GameFlow {
    private GUI clicked;
    private GUI blokusGUI;
    private GameBoard gameBoard;
    private GameLogic logic;
    private Boolean gameOver = false;
    private Boolean redHasNoMoves, blueHasNoMoves, greenHasNoMoves, yellowHasNoMoves = false;
    private PieceColor blue = PieceColor.BLUE;
    private PieceColor yellow = PieceColor.YELLOW;
    private PieceColor red = PieceColor.RED;
    private PieceColor green = PieceColor.GREEN;
    private PieceColor currentPlayer = blue;  //Trying to experiment with switching turns 
    private Piece piece;
    private Piece lastPieceRed;
    private Piece lastPieceBlue;
    private Piece lastPieceGreen;
    private Piece lastPieceYellow;
    EndGameScreen endGameScreen;
    Piece[] invBlue, invRed, invGreen, invYellow, currentInventory;
    
    public GameFlow() {
        this.logic = new GameLogic();
    }

    void createSinglePlayerGame(GameBoard gameBoard) {

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
            playerTurn(red, invRed, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(blue, invBlue, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(green, invGreen, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(yellow, invYellow, piece, gameBoard, clicked.getX(), clicked.getY());
        }

        while (!gameOver) {
            playerTurn(red, invRed, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(blue, invBlue, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(green, invGreen, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(yellow, invYellow, piece, gameBoard, clicked.getX(), clicked.getY());
            endGameCheck();
        }

    }

    void createTwoPlayerGame(GameBoard gameBoard) {
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
            playerTurn(red, invRed, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(blue, invBlue, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(green, invGreen, piece, gameBoard, clicked.getX(), clicked.getY());
            logic.validFirstMove(piece, clicked.getX(), clicked.getY());
            playerTurn(yellow, invYellow, piece, gameBoard, clicked.getX(), clicked.getY());
        }

        while (!gameOver) {

            playerTurn(red, invRed, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(blue, invBlue, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(green, invGreen, piece, gameBoard, clicked.getX(), clicked.getY());
            playerTurn(yellow, invYellow, piece, gameBoard, clicked.getX(), clicked.getY());
            endGameCheck();

        }
    }

    //Experimenting with switching turn logic
    void switchPlayer(){
        switch (currentPlayer) {
            case BLUE:
                currentPlayer = PieceColor.BLUE;
                break;
            case YELLOW:
                currentPlayer = PieceColor.GREEN;
                break;
            case RED:
                currentPlayer = PieceColor.YELLOW;
                break;
            case GREEN:
                currentPlayer = PieceColor.RED;
                break;
        }
    }

    // MT: trying to work through logic of player turn
    void playerTurn(PieceColor color, Piece[] pieceInventory, Piece piece, GameBoard gameBoard, int xCoordinate, int yCoordinate) {

        setCurrentPlayer(color);
        setCurrentInventory(pieceInventory);
         
        //Here we'll place Jonah's method to check for available moves first
        Move availableMove = logic.findMove(pieceInventory, gameBoard);
        if(availableMove == null){
            setHasNoMoves(color);
            System.out.println("Sorry you have no available moves! Skipping your turn....");
        }else{
            //Selects piece to play
            logic.pieceSelect(piece);
            //Checks if piece selected is legal
            boolean pieceIsLegal = gameBoard.isPieceLegal(piece, xCoordinate, yCoordinate);
            //IF statement to execute logic based on if piece is legal 
            if(!pieceIsLegal){
                System.out.println("This move is not legal. Please try again!");
            }else{
                //Places piece on board
                gameBoard.placePiece(piece, xCoordinate, yCoordinate);
                //Method to keep track of last piece for each color
                setLastPieceAs(color, piece);
                }
        }
        switchPlayer();
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

    //MT method to keep track of no moves for each color for end game check
    void setHasNoMoves(PieceColor color){
        
        if(color == red){
            redHasNoMoves = true;
        }else if(color == blue){
            blueHasNoMoves = true;
        }else if (color == green){
            greenHasNoMoves = true;
        }else{
            yellowHasNoMoves = true;
        }
    }

    //Mt Checks the status of all the variables for no available moves
    /*void endGameCheck(){

     if(redHasNoMoves == true && blueHasNoMoves == true && greenHasNoMoves == true && yellowHasNoMoves == true){
        endGame();
       }

    }*/

    //Ends the game
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setGUI(GUI gui) {
        this.blokusGUI = gui;
    }
    
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    public void setGameLogic(GameLogic gameLogic) {
        this.logic = gameLogic;
    }

    public void setCurrentPlayer(PieceColor color){
        currentPlayer = color;
    }

    public void setCurrentInventory(Piece[] inventory){
        currentInventory = inventory;
    }

    public PieceColor getCurrentPlayer(){
        return currentPlayer;
    }
    
    public Piece[] getCurrentInventory(){
        return currentInventory;
    }
}
