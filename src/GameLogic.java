public class GameLogic extends GUI{
    
    Piece piece;
    int amountOfPieces = 21;
    Boolean player1Turn;

    public void setPlayer1Turn(){

    }

    public void player1Turn(){
        player1Turn = true;
        //Code to implement for player 1 turn
        player1Turn = false;
    }

    public void player2Turn(){  //May not need this second method if want to just call a playerTurn method and use boolean to signal which player object
        player1Turn = false;
        //Code to implement for player 2 turn
        player1Turn = true;
    }

    public boolean isValidMove(){
        return true;
    }

    public boolean allPiecesPlayed(){
        if(amountOfPieces == 0){
            return true;
        }
        return false;
    }

    public Piece pieceSelect(Piece piece){
        this.piece = piece;
        return piece;
    }

    public Piece playPiece(int row, int column,Piece piece){
        return piece;
    }

    //My idea is using getters to pass in coordinates from the action listener for row and column
    public Boolean validFirstMove(int row, int column, Piece piece){  
        if((row == 0 && column == 19) || (row == 0 && column == 0) || (row == 19 && column == 0) || (row == 19 && column == 19)){
                throw new IllegalArgumentException("Must place first piece in a corner!");
                
        }
        else{
            return true;
        }

    //Assuming we are using int[][] to represent column and rows 
    }

}
