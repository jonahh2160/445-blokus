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

    // JH: Verifies if a piece is valid for Turn 1 by placing it in a blank board
    public Boolean validFirstMove(Piece piece, int x, int y) {
        GameBoard testBoard = new GameBoard();
        testBoard.placePiece(piece, x, y);

        int col = piece.getColor();
        int topLeft = testBoard.getSpaceValue(0, 0);
        int topRight = testBoard.getSpaceValue(19, 0);
        int botLeft = testBoard.getSpaceValue(0, 19);
        int botRight = testBoard.getSpaceValue(19, 19);

        if (topLeft == col || topRight == col || botLeft == col || botRight == col) {
            return true;
        } else {
            return false;
        }
    }

    // AR, JH: Returns the first valid move for a certain color it finds
    public Move findMove(Piece[] inv, GameBoard gameboard) {
        if (inv == null) {
            System.err.println("Error: Piece inventory is null.");
            return null;
        }

        int[][] board = gameboard.getGameBoard();
        int col = inv[0].getColor();

        // Detect corners of the same color
        for (int j = 0; j < board[j].length; j++) {
            for (int i = 0; i < board.length; i++) {
                if (gameboard.getSpaceValue(i, j) == col) {
                    // TODO: If a corner is empty, check its cross sections
                    // If all cross sections succeed, then attempt to place pieces
                    // If a piece is legal, then return that move and its coordinates

                    // Check diagonals of the current coordinates: if any of them are occupied,
                    // continue to the next coordinate
                    if (i - 1 >= 0 && j - 1 >= 0 && gameboard.getSpaceValue(i - 1, j - 1) == 0) {
                        // Process corner
                    }
                    if (i + 1 < board[0].length && j - 1 >= 0 && gameboard.getSpaceValue(i + 1, j - 1) == 0) {
                        // Process corner
                    }
                    if (i - 1 >= 0 && j + 1 < board.length && gameboard.getSpaceValue(i - 1, j + 1) == 0) {
                        // Process corner
                    }
                    if (i + 1 < board[0].length && j + 1 < board.length && gameboard.getSpaceValue(i + 1, j + 1) == 0) {
                        // Process corner
                    }
                }
            }
        }
        // TODO: Then try placing every piece left in the inventory in every
        // orientation, offset by piece's w and h?
        // TODO: Return the first move that works

        // return new Move(piece, x, y);

        return null;
    }

    private boolean crossTest(int x, int y, GameBoard gameboard, int[][] board) {
        // Check whether corner would rub up against another piece
        if (x - 1 >= 0 && gameboard.getSpaceValue(x - 1, y) == col) {
            return false;
        } else if (x + 1 < board[0].length && gameboard.getSpaceValue(x + 1, y) == col) {
            return false;
        } else if (y - 1 >= 0 && gameboard.getSpaceValue(x, y - 1) == col) {
            return false;
        } else if (y + 1 < board.length && gameboard.getSpaceValue(x, y + 1) == col) {
            return false;
        }

        return true;
    }

}
