// MT 



public class GameFlow{
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
    
    public GameFlow(GameBoard gameBoard) {
        this.logic = new GameLogic();
        this.gameBoard = gameBoard;
        invBlue = gameBoard.createInvPieces(blue);
        invGreen = gameBoard.createInvPieces(green);
        invRed = gameBoard.createInvPieces(red);
        invYellow = gameBoard.createInvPieces(yellow);
    }

    Piece[] removePieceFromInventory (Piece piece, PieceColor color ){
        Piece[] currentInventory;

        //Sets inventory for piece to be removed from
        if(color == red){
            currentInventory = invRed;
        }else if(color == blue){
            currentInventory = invBlue;
        }else if(color == green){
            currentInventory = invGreen;
        }else{
            currentInventory = invYellow;
        }

        int indexToRemove = -1;

        //Finds index of piece to be removed
        for (int i = 0; i < currentInventory.length; i++) {
        if (currentInventory[i] == piece) {
            indexToRemove = i;
            break;
        }
    }
        //Creates a new array without piece that was played
         if (indexToRemove != -1) {
            Piece[] updatedInventory = new Piece[currentInventory.length - 1];
    
            System.arraycopy(currentInventory, 0, updatedInventory, 0, indexToRemove);
            System.arraycopy(currentInventory, indexToRemove + 1, updatedInventory, indexToRemove,
                    currentInventory.length - indexToRemove - 1);

            // Updates inventory without piece
            if (color == PieceColor.RED) {
             invRed = updatedInventory;
            } else if (color == PieceColor.BLUE) {
                invBlue = updatedInventory;
            } else if (color == PieceColor.GREEN) {
                invGreen = updatedInventory;
            } else {
                invYellow = updatedInventory;
        }
    }
    //Returns Array without piece;
    return currentInventory; 
}

    

    void createSinglePlayerGame(GameBoard gameBoard) {

        //invBlue = gameBoard.createInvPieces(blue);
        //invGreen = gameBoard.createInvPieces(green);
        //invRed = gameBoard.createInvPieces(red);
        //invYellow = gameBoard.createInvPieces(yellow);

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
    void switchPlayer() {
        // Switch the current player to the next one in the sequence: blue -> yellow -> red -> green -> blue
        switch (currentPlayer) {
            case BLUE:
                currentPlayer = PieceColor.YELLOW;
                break;
            case YELLOW:
                currentPlayer = PieceColor.RED;
                break;
            case RED:
                currentPlayer = PieceColor.GREEN;
                break;
            case GREEN:
                currentPlayer = PieceColor.BLUE;
                break;
        }
        System.out.println("Current Player " + currentPlayer);
    }

    void switchInventory() {
        switch(currentPlayer) {
                case BLUE:
                    currentInventory = invYellow;
                    break;
                case YELLOW:
                    currentInventory = invRed;
                    break;
                case RED:
                    currentInventory = invGreen;
                    break;
                case GREEN:
                    currentInventory = invBlue;
                    break;
            }
            System.out.println("Current Inventory " + currentPlayer);
            
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
    void endGameCheck(){

     if(redHasNoMoves == true && blueHasNoMoves == true && greenHasNoMoves == true && yellowHasNoMoves == true){
        endGame();
       }

    }

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
