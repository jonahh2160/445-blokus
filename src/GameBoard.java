// JH 4/17

public class GameBoard {

    // Space status: 0: Empty, 1: Blue, 2: Yellow, 3: Red, 4 Green

    // Instantiate variables
    private int[][] board;

    // Constructor
    public GameBoard() {
        // Initialize the board of 400 squares
        board = new int[20][20];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    // Returns a list of Pieces for a certain color for start of game
    public Piece[] createInvPieces(PieceColor col) {
        Piece[] inv = new Piece[21];

        inv[0] = new Piece(Piece.Type.ONE, col);
        inv[1] = new Piece(Piece.Type.TWO, col);
        inv[2] = new Piece(Piece.Type.THREE, col);
        inv[3] = new Piece(Piece.Type.CORNER, col);
        inv[4] = new Piece(Piece.Type.LINE, col);
        inv[5] = new Piece(Piece.Type.LBLOCK, col);
        inv[6] = new Piece(Piece.Type.ZBLOCK, col);
        inv[7] = new Piece(Piece.Type.SQUARE, col);
        inv[8] = new Piece(Piece.Type.TBLOCK, col);
        inv[9] = new Piece(Piece.Type.F, col);
        inv[10] = new Piece(Piece.Type.I, col);
        inv[11] = new Piece(Piece.Type.L, col);
        inv[12] = new Piece(Piece.Type.N, col);
        inv[13] = new Piece(Piece.Type.P, col);
        inv[14] = new Piece(Piece.Type.BIGT, col);
        inv[15] = new Piece(Piece.Type.U, col);
        inv[16] = new Piece(Piece.Type.V, col);
        inv[17] = new Piece(Piece.Type.W, col);
        inv[18] = new Piece(Piece.Type.X, col);
        inv[19] = new Piece(Piece.Type.Y, col);
        inv[20] = new Piece(Piece.Type.BIGZ, col);

        return inv;
    }

    public int[][] getGameBoard() {
        return board;
    }

    public int getSpaceValue(int x, int y) {
        return board[y][x];
    }

    // EW
    public void setSpaceValue(int x, int y, int value) {
        //Validate coordinates are within the bounds of the board
        if (x >= 0 && x < board[0].length && y >= 0 && y < board.length) {
            //Set the value of the space at the specified coordinates
            board[y][x] = value;
        } else {
            //If the coordinates are out of bounds, you can choose to throw an exception
            //or handle the invalid input in another way, such as logging a warning
            System.out.println("Invalid coordinates: (" + x + ", " + y + "). Cannot set space value.");
        }
    }

    public boolean isPieceLegal(Piece piece, int x, int y) {
        int w = piece.getWidth();
        int h = piece.getHeight();
        int col = piece.getColor();

        // Check if the piece would go out of bounds
        if (x + w > board[0].length || y + h > board.length) {
            return false;
        }

        // Check if affected spaces are occupied
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (getSpaceValue(x + i, y + j) != 0) {
                    return false;
                }
            }
        }

        // Check if the piece complies with the corner rule
        boolean validCorner = false;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (piece.getCoordValue(i, j) == col) {
                    int curX = x + i;
                    int curY = y + j;
                    // These if branches are a bit complicated. We have to make sure we're not checking OOB values
                    // But since we haven't actually placed the piece yet, we don't have to account for its values

                    // Check whether piece is rubbing up against another piece
                    if (curX - 1 >= 0 && getSpaceValue(curX - 1, curY) == col) {
                        return false;
                    } else if (curX + 1 < board[0].length && getSpaceValue(curX + 1, curY) == col) {
                        return false;
                    } else if (curY - 1 >= 0 && getSpaceValue(curX, curY - 1) == col) {
                        return false;
                    } else if (curY + 1 < board.length && getSpaceValue(curX, curY + 1) == col) {
                        return false;
                    }

                    // Check diagonals of current coords to see if piece makes corner contact
                    if (curX - 1 >= 0 && curY - 1 >= 0 && getSpaceValue(curX -1, curY - 1) == col) {
                        validCorner = true;
                    } else if (curX + 1 < board[0].length && curY - 1 >= 0 && getSpaceValue(curX + 1, curY -1) == col) {
                        validCorner = true;
                    } else if (curX - 1 >= 0 && curY + 1 < board.length && getSpaceValue(curX - 1, curY + 1) == col) {
                        validCorner = true;
                    } else if (curX + 1 < board[0].length && curY + 1 < board.length && getSpaceValue(curX + 1, curY + 1) == col) {
                        validCorner = true;
                    }
                }
            }
        }

        // Makes sure the previous branch found at least one valid corner
        if (!validCorner) {
            return false;
        }

        return true;
    }

    // Returns a list of each coordinate pair where the piece would be placed
    public int[][] tryPiece(Piece piece, int x, int y) {
        int w = piece.getWidth();
        int h = piece.getHeight();
        int[][] pairs = new int[w * h][2];

        // Check if the piece would go out of bounds
        if (x + w > board[0].length || y + h > board.length) {
            throw new IllegalArgumentException("Piece placement would go out of bounds!");
        } else {
            // Keep track of the current coordinate pair separately
            int pairNo = 0;
            // Check the entire row first (i loop) before going to the next row (j loop)
            for (int j = 0; j < h; j++) {
                for (int i = 0; i < w; i++) {
                    pairs[pairNo][0] = x + i;
                    pairs[pairNo][1] = y + j;
                    pairNo++;
                }
            }

            System.out.println("Pairs:");
            for (int i = 0; i < pairs.length; i++) {
                System.out.println("(" + pairs[i][0] + ", " + pairs[i][1] + ")");
            }

            return pairs;
        }
    }

    // Actually places the piece at the specified location EW
    public void placePiece(Piece piece, int x, int y) {
        int pieceWidth = piece.getWidth();  // Width of the piece
        int pieceHeight = piece.getHeight(); // Height of the piece
        
        // Determine the width and height of the game board
        int boardWidth = board[0].length;  // Width of the board
        int boardHeight = board.length;    // Height of the board
    
        // Check if the piece would go out of bounds horizontally or vertically
        if (y + pieceWidth > boardWidth || x + pieceHeight > boardHeight) {
            // Print diagnostic information
            System.out.println("Piece dimensions: Width=" + pieceWidth + ", Height=" + pieceHeight);
            System.out.println("Piece placement would go out of bounds horizontally at (" + (x + pieceWidth) + ", " + y + ") or vertically at (" + x + ", " + (y + pieceHeight) + ")");
    
            // Throw an exception if the piece placement would go out of bounds
            throw new IllegalArgumentException("Piece placement would go out of bounds at (" + (x + pieceWidth) + ", " + y + ") or (" + x + ", " + (y + pieceHeight) + ")");
        } else {
            // Overwrite cells in the game board with the piece's color
            for (int i = 0; i < pieceWidth; i++) {
                for (int j = 0; j < pieceHeight; j++) {
                    // Only color parts of the piece that AREN'T 0 in its layout
                    if (piece.getCoordValue(i, j) != 0) {
                        board[x + j][y + i] = piece.getColor();
                    }
                }
            }
        }
    }

    // Iterates through the board to calculate P1's score
    public int calcScoreP1(Piece lastBlue, Piece lastRed) {
        // Minimum scores: -89 blue and -89 red
        int scoreBlue = -89;
        int scoreRed = -89;

        // Count the number of Blue and Red squares
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    scoreBlue++;
                } else if (board[i][j] == 3) {
                    scoreRed++;
                }
            }
        }

        // If there are no blue pieces remaining, assign bonus points
        if (scoreBlue == 0) {
            scoreBlue += 15;
            if (lastBlue.getType().equals(Piece.Type.ONE)) {
                scoreBlue += 5;
            }
        }

        // If there are no red pieces remaining, assign bonus points
        if (scoreRed == 0) {
            scoreRed += 15;
            if (lastRed.getType().equals(Piece.Type.ONE)) {
                scoreRed += 5;
            }
        }

        return scoreBlue + scoreRed;
    }

    // Iterates through the board to calculate P2's score
    public int calcScoreP2(Piece lastYellow, Piece lastGreen) {
        // Minimum scores: -89 yellow and -89 green
        int scoreYellow = -89;
        int scoreGreen = -89;

        // Count the number of Yellow and Green squares
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) {
                    scoreYellow++;
                } else if (board[i][j] == 4) {
                    scoreGreen++;
                }
            }
        }

        // If there are no yellow pieces remaining, assign bonus points
        if (scoreYellow == 0) {
            scoreYellow += 15;
            if (lastYellow.getType().equals(Piece.Type.ONE)) {
                scoreYellow += 5;
            }
        }

        // If there are no green pieces remaining, assign bonus points
        if (scoreGreen == 0) {
            scoreGreen += 15;
            if (lastGreen.getType().equals(Piece.Type.ONE)) {
                scoreGreen += 5;
            }
        }

        return scoreYellow + scoreGreen;
    }

    // Test method: print the status of the board
    public void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
