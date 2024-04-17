// JH 4/11

public enum PieceColor {
    BLUE(1),
    YELLOW(2),
    RED(3),
    GREEN(4);

    private final int col;

    PieceColor(int col) {
        this.col = col;
    }

    public int getColorNo() {
        return col;
    }
}
