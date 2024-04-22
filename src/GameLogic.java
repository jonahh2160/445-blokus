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

    public boolean isValidMove() {
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
        int[][] board = gameboard.getGameBoard();
        int col = inv[0].getColor();

        // Detect corners of the same color
        for (int j = 0; j < board[j].length; j++) {
            for (int i = 0; i < board.length; i++) {
                if (gameboard.getSpaceValue(i, j) == col) {
                    // Same logic as in GameBoard.java's isPieceLegal() method
                    // TODO: Fix this to account for existing pieces already set down (old logic doesn't account for existing)
                    // Check whether piece is rubbing up against another piece
                    if (i - 1 >= 0 && gameboard.getSpaceValue(i - 1, j) == col) {
                        continue;
                    } else if (i + 1 < board[0].length && gameboard.getSpaceValue(i + 1, j) == col) {
                        continue;
                    } else if (j - 1 >= 0 && gameboard.getSpaceValue(i, j - 1) == col) {
                        continue;
                    } else if (j + 1 < board.length && gameboard.getSpaceValue(i, j + 1) == col) {
                        continue;
                    }

                    // Check diagonals of current coords to see if piece makes corner contact
                    if (i - 1 >= 0 && j - 1 >= 0 && gameboard.getSpaceValue(i -1, j - 1) == col) {
                        // Logic here
                    } else if (i + 1 < board[0].length && j - 1 >= 0 && gameboard.getSpaceValue(i + 1, j -1) == col) {
                        // Logic here
                    } else if (i - 1 >= 0 && j + 1 < board.length && gameboard.getSpaceValue(i - 1, j + 1) == col) {
                        // Logic here
                    } else if (i + 1 < board[0].length && j + 1 < board.length && gameboard.getSpaceValue(i + 1, j + 1) == col) {
                        // Logic here
                    }
                }
            }
        }
        // TODO: Then try placing every piece left in the inventory in every orientation, offset by piece's w and h?
        // TODO: Return the first move that works

        // return new Move(piece, x, y);

        return null;
    }

}
