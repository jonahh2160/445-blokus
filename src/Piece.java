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

    private Type type;
    private Color color;
    private int[][] layout;
    private byte value = 0;
    private int rotation = 0;
    private boolean selected = false;

    // TODO: Figure out how to store the layout of the piece and/or its position

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
        initializePiece(type);
        printLayout();
    }

    // Rotate the 2D array clockwise
    public void rotateRight() {
        rotation = (rotation + 1) % 4;

        // TODO: Write logic to rotate array
    }

    // Rotate the 2D array counter-clockwise
    public void rotateLeft() {
        rotation = (rotation - 1) % 4;

        // TODO: Write logic to rotate array
    }

    public void select() {
        selected = true;
    }

    public void unselect() {
        selected = false;
    }

    private void initializePiece(Type type) {
        switch (type) {
            case ONE:
                layout = new int[][] { { 1 } };
                value = 1;
                break;
            case TWO:
                layout = new int[][] { { 1, 1 } };
                value = 2;
                break;
            case THREE:
                layout = new int[][] { { 1, 1, 1 } };
                value = 3;
                break;
            case CORNER:
                layout = new int[][] { { 0, 1 }, { 1, 1 } };
                value = 3;
                break;
            case LINE:
                layout = new int[][] { { 1, 1, 1, 1 } };
                value = 4;
                break;
            case LBLOCK:
                layout = new int[][] { { 0, 0, 1 }, { 1, 1, 1 } };
                value = 4;
                break;
            case ZBLOCK:
                layout = new int[][] { { 1, 1, 0 }, { 0, 1, 1 } };
                value = 4;
                break;
            case SQUARE:
                layout = new int[][] { { 1, 1 }, { 1, 1 } };
                value = 4;
                break;
            case TBLOCK:
                layout = new int[][] { { 1, 1, 1 }, { 0, 1, 0 } };
                value = 4;
                break;
            case F:
                layout = new int[][] { { 0, 1, 1 }, { 1, 1, 0 }, { 0, 1, 0 } };
                value = 5;
                break;
            case I:
                layout = new int[][] { { 1 }, { 1 }, { 1 }, { 1 }, { 1 } };
                value = 5;
                break;
            case L:
                layout = new int[][] { { 1, 0 }, { 1, 0 }, { 1, 0 }, { 1, 1 } };
                value = 5;
                break;
            case N:
                layout = new int[][] { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, 0 } };
                value = 5;
                break;
            case P:
                layout = new int[][] { { 1, 1 }, { 1, 1 }, { 1, 0 } };
                value = 5;
                break;
            case BIGT:
                layout = new int[][] { { 1, 1, 1 }, { 0, 1, 0 }, { 0, 1, 0 } };
                value = 5;
                break;
            case U:
                layout = new int[][] { { 1, 0, 1 }, { 1, 1, 1 } };
                value = 5;
                break;
            case V:
                layout = new int[][] { { 0, 0, 1 }, { 0, 0, 1 }, { 1, 1, 1 } };
                value = 5;
                break;
            case W:
                layout = new int[][] { { 0, 0, 1 }, { 0, 1, 1, }, { 1, 1, 0 } };
                value = 5;
                break;
            case X:
                layout = new int[][] { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 1, 0 } };
                value = 5;
                break;
            case Y:
                layout = new int[][] { { 0, 1 }, { 1, 1 }, { 0, 1 }, { 0, 1 } };
                value = 5;
                break;
            case BIGZ:
                layout = new int[][] { { 1, 1, 0 }, { 0, 1, 0 }, { 0, 1, 1 } };
                value = 5;
                break;
            default:
                System.out.println("Unknown type");
                break;
        }
    }

    private void printLayout() {
        System.out.println("Layout:");
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                System.out.print(layout[i][j] + " ");
            }
            System.out.println();
        }
    }

}
