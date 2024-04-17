public class GameFlow {
    Boolean gameOver = false;
    Boolean player1Turn = true;
    Player player1;
    Player player2;
    Player cpu;
    GameBoard gameBoard;
    int numberOfPieces = 21;
    PieceColor color;


    void createSinglePlayerGame(Player player, Player cpu, GameBoard gameBoard, PieceColor red, PieceColor blue, PieceColor green, PieceColor yellow, 
    int numberOfPieces){

        while(!gameOver){
            playerTurn(red, player, numberOfPieces, gameBoard);
            playerTurn(blue, player, numberOfPieces, gameBoard);
            playerTurn(green, player, numberOfPieces, gameBoard);
            playerTurn(yellow, player, numberOfPieces, gameBoard);


            //player1Turn(player, numberOfPieces);
           // cpuTurn(cpu);
        }

    }

    void createTwoPlayerGame(Player player, Player cpu, GameBoard gameBoard, PieceColor red, PieceColor blue, PieceColor green, PieceColor yellow, 
    int numberOfPieces){

        while(!gameOver){

            playerTurn(red, player, numberOfPieces, gameBoard);
            playerTurn(blue, player, numberOfPieces, gameBoard);
            playerTurn(green, player, numberOfPieces, gameBoard);
            playerTurn(yellow, player, numberOfPieces, gameBoard);

            //player1Turn(player1, numberOfPieces);
            //player2Turn(player2, numberOfPieces);

        }
    }

    void player1Turn(Player player1, int numberOfPieces){

        //Logic for player1 turn;
    }

    void player2Turn(Player player2){
        //Logic for player2 turn
    }

    void cpuTurn(Player cpu){
        //Logic for cpu turn
    }

    void playerTurn(PieceColor color, Player player, int numberOfPiecesj, GameBoard gameBoard){
        //Logic for player turn;
    }

    void endGame(){
        //Logic for player turn;
    }
}