//Outline for now 

//TODO: connect the pieces to the players
//TODO: Account for COM player   

import java.util.ArrayList; 

public class ScoreCalculator
{
    private int[] unplayedPieces; //All the pieces that are unplayed for that player 
    private boolean allPiecesPlayed = false; //All pieces that have been played in the game 
    private boolean lastPieceOne = false; //If the last piece to be played was the single square 

    int score = calculateScore(unplayedPieces, allPiecesPlayed, lastPieceOne);

    public getAllPiecesPlayed()
    {
        return piecesPlayed; 
    }
    public void setAllPiecesPlayed(boolean allPiecesPlayed)
    {
        this.allPiecesPlayed = allPiecesPlayed; 
    }
    public getLastPieceOne()
    {
        return lastPieceOne; 
    }
    public void setLastPieceOne(boolean lastPieceOne)
    {
        this.lastPieceOne = lastPieceOne;
    }

    public static int calculateScore(int[] unplayedPieces, boolean piecesPlayed, boolean lastPieceOne)
    {
        int score = 0;

        //Subtracting points for unplayed pieces 
        for(int pieceSize : unplayedPieces)
        {
            score -= pieceSize; 
        }

        if(piecesPlayed)
        {
            score += 15; 
        }

        if(lastPieceOne)
        {
            score += 5;
        }

        return score;
    }

    //To declare winner
    /*just putting here for now for outline, can go wherever needs to */

    /*if(player1.score < player2.score)
    {
        //player1 wins, display score
    }
    else if(player1.score > player2.score)
    {
        //player2 wins, display score 
    }
    else 
    {
        //its a tie! 
    }
    */
}

