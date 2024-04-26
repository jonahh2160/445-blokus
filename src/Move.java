// JH 4/20

// This class is used specifically for GameLogic's findMove method
public class Move {
    private Piece piece;
    private int x;
    private int y;

    // Constructor
    public Move(Piece piece, int x, int y) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    public void printMove() {
        piece.printLayout();
        System.out.println("(" + x + ", " + y + ")");
    }

    // Getters
    public Piece getPiece() {
        return piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Setters
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
