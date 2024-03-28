import java.awt.Color;

public class Piece {

    public enum Type {
        // See https://en.wikipedia.org/wiki/Blokus#/media/File:Blokus_tiles.svg
        ONE,
        TWO,
        THREE,
        CORNER,
        LINE,
        LBLOCK,
        ZBLOCK,
        SQUARE,
        TBLOCK,
        F,
        I,
        L,
        N,
        P,
        BIGT,
        U,
        V,
        W,
        X,
        Y,
        BIGZ
    }

    private byte type = -1;
    private Color color = null;
    private boolean selected = false;
    private int rotation = 0;
    // TODO: Figure out how to store the layout of the piece and/or its position

    public Piece() {
        // TODO: Constructor; requires a color and a type. Rot and selected will stay at
        // default values. Layout will be set based on type and rotation.
    }

    public void rotate() {
        rotation = (rotation + 1) % 4;
        // TODO: get the new layout of the piece
    }

}
