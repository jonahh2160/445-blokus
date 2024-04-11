// JH 4/10

public class GameBoard {

    // -1: Empty, 0: Blue, 1: Yellow, 2: Red, 3 Green

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
