// MT 4/10

public class GameLogic {

    Piece piece;
    int amountOfPieces = 21;
    Boolean player1Turn;

    public void setPlayer1Turn() {

    }

    public void player1Turn() {
        player1Turn = true;
        // Code to implement for player 1 turn
        player1Turn = false;
    }

    public void player2Turn() { // May not need this second method if want to just call a playerTurn method and
                                // use boolean to signal which player object
        player1Turn = false;
        // Code to implement for player 2 turn
        player1Turn = true;
    }

    public boolean isValidMove(Piece piece, int x, int y) {
        return true;

    }
    

    public boolean allPiecesPlayed() {
        if (amountOfPieces == 0) {
            return true;
        }
        return false;
    }

    public Piece pieceSelect(Piece piece) {
        this.piece = piece;
        return piece;
    }

    //EW
    public Boolean validFirstMove(Piece piece, int x, int y) {
        // Define the coordinates for the corners of the board
        int[][] corners = {
            {0, 0}, // Top-left corner
            {0, 19}, // Top-right corner
            {19, 0}, // Bottom-left corner
            {19, 19} // Bottom-right corner
        };
        
        // Get the shape of the piece as a 2D array
        int[][] shape = piece.getShape();
        int pieceWidth = shape[0].length; // Width of the piece
        int pieceHeight = shape.length; // Height of the piece
        
        System.out.println("Checking piece placement at starting point (x, y): (" + x + ", " + y + ")");
        
        // Iterate through the piece's shape
        for (int i = 0; i < pieceWidth; i++) {
            for (int j = 0; j < pieceHeight; j++) {
                // Only check cells that are part of the piece's shape
                if (shape[j][i] != 0) {
                    // Calculate the actual row and column on the board
                    int actualRow = x + j;
                    int actualCol = y + i;
                    
                    System.out.println("Checking piece cell at (" + actualRow + ", " + actualCol + ")");
                    
                    // Check if the current cell matches any corner
                    for (int[] corner : corners) {
                        if (actualRow == corner[0] && actualCol == corner[1]) {
                            // If any part of the piece occupies a corner, print diagnostic information and return true
                            System.out.println("Piece cell at (" + actualRow + ", " + actualCol + ") matches corner at (" + corner[0] + ", " + corner[1] + ")");
                            return true;
                        }
                    }
                }
            }
        }
        
        // If no part of the piece occupies a corner, print diagnostic information and return false
        System.out.println("Piece does not occupy any corner");
        return false;
    }

    // AR, JH: Returns the first valid move for a certain color it finds
    public Move findMove(Piece[] inv, GameBoard gameboard) {
        if (inv == null) {
            System.err.println("Error: Piece inventory is null.");
            return null;
        }

        int[][] board = gameboard.getGameBoard();
        int col = inv[0].getColor();

        // Attempt to find possible moves by checking empty cells
        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i < board[j].length; i++) {
                if (gameboard.getSpaceValue(i, j) == 0) {
                    // If there is a corner there, start checking pieces placed there and at various
                    // orientations. Offset it in some way by piece wh in respect to rotation

                    // Check whether spot would rub up against a piece of target color
                    if (i - 1 >= 0 && gameboard.getSpaceValue(i - 1, j) == col) {
                        continue;
                    } else if (i + 1 < board[0].length && gameboard.getSpaceValue(i + 1, j) == col) {
                        continue;
                    } else if (j - 1 >= 0 && gameboard.getSpaceValue(i, j - 1) == col) {
                        continue;
                    } else if (j + 1 < board.length && gameboard.getSpaceValue(i, j + 1) == col) {
                        continue;
                    }

                    // If it doesn't rub up, start testing pieces once any valid corner is found
                    int cornerType = 0;
                    if (i - 1 >= 0 && j - 1 >= 0 && gameboard.getSpaceValue(i - 1, j - 1) == col) {
                        // Top Left type
                        cornerType = 1;
                    } else if (i + 1 < board[0].length && j - 1 >= 0 && gameboard.getSpaceValue(i + 1, j - 1) == col) {
                        // Top Right type
                        cornerType = 2;
                    } else if (i - 1 >= 0 && j + 1 < board.length && gameboard.getSpaceValue(i - 1, j + 1) == col) {
                        // Bottom Left type
                        cornerType = 3;
                    } else if (i + 1 < board[0].length && j + 1 < board.length
                            && gameboard.getSpaceValue(i + 1, j + 1) == col) {
                        // Bottom Right type
                        cornerType = 4;
                    }

                    if (cornerType != 0) {
                        // Try pieces in the inventory. Return the first valid move
                        for (int k = 0; k < inv.length; k++) {
                            Piece currentPiece = inv[k];
                            int x, y;

                            // TODO: Only rotate the piece if it would have an effect
                            // TODO: Determine the appropriate offset for placement of the piece (relative)

                            // Default rotation
                            x = calcOffsetX(i, cornerType, currentPiece);
                            y = calcOffsetY(j, cornerType, currentPiece);
                            if (gameboard.isPieceLegal(currentPiece, x, y)) {
                                return new Move(currentPiece, x, y);
                            }

                            // Rotate once
                            currentPiece.rotateRight();
                            if (gameboard.isPieceLegal(currentPiece, x, y)) {
                                return new Move(currentPiece, x, y);
                            }

                            // Rotate twice
                            currentPiece.rotateRight();
                            if (gameboard.isPieceLegal(currentPiece, x, y)) {
                                return new Move(currentPiece, x, y);
                            }

                            // Rotate thrice
                            currentPiece.rotateRight();
                            if (gameboard.isPieceLegal(currentPiece, x, y)) {
                                return new Move(currentPiece, x, y);
                            }
                        }
                    } else {
                        // No corner of target color was found, check next empty cell
                        continue;
                    }
                }
            }
        }

        // If no piece works, then there are no more possible moves
        return null;
    }

    // JH: Helper method for findMove()—calculates x offset based on corner location
    private int calcOffsetX(int x, int cornerType, Piece piece) {
        int newX = x;
        int w = piece.getWidth() - 1;

        if (cornerType == 2) {
            newX = x - w;
        } else if (cornerType == 4) {
            newX = x - w;
        }

        return newX;
    }

    // JH: Helper method for findMove()—calculates y offset based on corner location
    private int calcOffsetY(int y, int cornerType, Piece piece) {
        int newY = 0;
        int h = piece.getHeight() - 1;

        if (cornerType == 3) {
            newY = y - h;
        } else if (cornerType == 4) {
            newY = y - h;
        }

        return newY;
    }

}
