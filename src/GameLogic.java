public class GameLogic {
    
    Piece piece;
    Boolean player1Turn;

    public void setPlayer1Turn(Boolean ){

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
        return true;
    }

    public Piece pieceSelect(Piece piece){
        return null;
    }

    public Piece playPeice(Piece piece){
        return null;
    }

}
