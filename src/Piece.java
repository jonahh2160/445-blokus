// JH 4/11

import java.util.Objects;

public class Piece {

    // Enum containing the types of pieces
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

    // Initialize variables
    private Type type;
    private int color;
    private int[][] layout;
    private byte value = 0;
    private int width = 0;
    private int height = 0;
    private int rotation = 0;
    private boolean selected = false;

    // Constructor
    public Piece(Type type, PieceColor pieceColor) {
        this.type = type;
        this.color = pieceColor.getColorNo();
        makeLayout(type);
        colorLayout(color);
        updateDimensions();
    }

    // EW: Rotate the 2D array clockwise
    public void rotateRight() {
        rotation = (rotation + 1) % 4;
        int[][] rotatedLayout = new int[layout[0].length][layout.length];

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                rotatedLayout[j][layout.length - 1 - i] = layout[i][j];
            }
        }

        layout = rotatedLayout;
        updateDimensions();
    }

    // EW: Rotate the 2D array counter-clockwise
    public void rotateLeft() {
        rotation = (rotation - 1 + 4) % 4;
        int[][] rotatedLayout = new int[layout[0].length][layout.length];

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                rotatedLayout[layout[i].length - 1 - j][i] = layout[i][j];
            }
        }

        layout = rotatedLayout;
        updateDimensions();
    }

    // Creates the piece's layout in the 2D array
    private void makeLayout(Type type) {
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

    // Converts the layout into its correct color
    private void colorLayout(int color) {
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                if (layout[i][j] == 1) {
                    layout[i][j] = color;
                }
            }
        }
    }

    // Updates the width and height variables
    private void updateDimensions() {
        width = layout[0].length;
        height = layout.length;
    }

    // Test method; prints the appearance of the piece
    // 0: Empty, 1: Blue, 2: Yellow, 3: Red, 4 Green
    public void printLayout() {
        System.out.println("Layout:");
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                System.out.print(layout[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Dimensions: " + width + " x " + height);
    }

    public int getCoordValue(int x, int y) {
        return layout[y][x];
    }

    // Getter for color
    public int getColor() {
        return color;
    }

    // Getter for type
    public Type getType() {
        return type;
    }

    // Getter for width
    public int getWidth() {
        return width;
    }

    // Getter for height
    public int getHeight() {
        return height;
    }

    // Getter for rotation
    public int getRotation() {
        return rotation;
    }

    // Setter for rotation. Also changes the layout
    public void setRotation(int rotation) {
        int difference = this.rotation - rotation;

        while (difference != 0) {
            if (this.rotation > rotation) {
                rotateRight();
            } else if (this.rotation < rotation) {
                rotateLeft();
            }
            difference = this.rotation - rotation;
        }
    }
    //Overriding equals so we have  way to compare pieces
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Piece other = (Piece) obj;
        return type == other.type &&
                color == other.color &&
                rotation == other.rotation;
    }

    //MT gotta do this when you override the equals method according to 430
    @Override
    public int hashCode() {
        return Objects.hash(type, color, rotation);
    }
}
