public class TestApp {
    public static void main(String[] args) throws Exception {
        System.out.println("Testing");
        GameBoard gameboard = new GameBoard();
        GameLogic gamelogic = new GameLogic();

        Piece testPiece = new Piece(Piece.Type.TBLOCK, PieceColor.BLUE);
        gameboard.printBoard();
        // testPiece.printLayout();
        // gameboard.tryPiece(testPiece, 17, 18);
        // gameboard.placePiece(testPiece, 17, 18);
        // gameboard.printBoard();
        // System.out.println("P1 Score: " + gameboard.calcScoreP1(testPiece));
        // System.out.println("P2 Score: " + gameboard.calcScoreP2(testPiece));

        // testPiece.rotateLeft();
        // testPiece.rotateLeft();
        // testPiece.printLayout();
        // System.out.println(gamelogic.validFirstMove(testPiece, 0, 18));
        // testPiece.printLayout();
        // System.out.println(gamelogic.validFirstMove(testPiece, 17, 17));

    }
}