// JH 4/11

public class GameBoard {

    // Space status: 0: Empty, 1: Blue, 2: Yellow, 3: Red, 4 Green

    // Instantiate variables
    private int[][] board;
    private short blueSquares = 0;
    private short yellowSquares = 0;
    private short redSquares = 0;
    private short greenSquares = 0;

    // Constructor
    public GameBoard() {
        // Initialize the board of 400 squares
        board = new int[20][20];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = -1;
            }
        }

        printBoard();
    }

    // TODO: Method to place a piece? (Pass piece and location, offset by mouse pos)
    // TODO: Method to calculate score

    public int getSpaceValue(int x, int y) {
        return board[x][y];
    }

    // Returns a list of each coordinate pair where the piece would be placed
    public int[][] tryPiece(Piece piece, int x, int y) {
        int w = piece.getWidth();
        int h = piece.getHeight();
        int[][] pairs = new int[w * h][2];

        // Check if the piece would go out of bounds
        if (x + w > board[0].length || y + h > board.length) {
            System.out.println("FAILURE: Board dimensions are " + board[0].length + " x " + board.length + "!");
            return null;
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

    // Test method: print the status of the board
    private void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
