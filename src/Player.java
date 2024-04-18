public class Player {

    // Michael Toon 3/28/24

    private String name;
    private int color;
    private double score;
    private int pieces;
    private boolean lastPiece;

    public Player(String name, PieceColor color, double score, int pieces, boolean lastPiece) {
        this.name = name;
        this.color = color.getColorNo();
        this.score = score;
        this.pieces = pieces;
        lastPiece = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(PieceColor newColor) {
        this.color = newColor.getColorNo();
    }

    public double getScore() {
        return score;
    }

    public void setScore(double newScore) {
        this.score = newScore;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPeices(int newAmountOfPieces) {
        this.pieces = newAmountOfPieces;
    }

    public boolean isLastPiece() {
        return lastPiece;
    }

    public void setAsLastPiece(boolean thisIsLastPiece) {
        this.lastPiece = thisIsLastPiece;
    }

}
