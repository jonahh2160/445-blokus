public class Player2 {
    
    String name;
    String color;
    double score;
    int pieces;
    boolean lastPiece;
    

    public Player2(String name, String color, double score, int pieces, boolean lastPiece){
        this.name = name;
        this.color = color;
        this.score = score;
        this.pieces = pieces;
        lastPiece = false;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String newColor){
        this.color = newColor;
    }
    public double getScore(){
        return score;
    }

    public void setScore(double newScore){
        this.score = newScore;
    }

    public int getPieces(){
        return pieces;
    }

    public void setPeices(int newAmountOfPieces){
        this.pieces = newAmountOfPieces;
    }

    public boolean isLastPiece() {
        return lastPiece;
    }

    public void setAsLastPiece(boolean thisIsLastPiece) {
        this.lastPiece = thisIsLastPiece;
    }





}
