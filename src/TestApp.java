public class TestApp {
    public static void main(String[] args) throws Exception {
        System.out.println("Testing");
        GameBoard gameboard = new GameBoard();
        GameLogic gamelogic = new GameLogic();

        Piece testPiece = new Piece(Piece.Type.TBLOCK, PieceColor.BLUE);
        testPiece.printLayout();
        gameboard.tryPiece(testPiece, 17, 18);
        gameboard.placePiece(testPiece, 17, 18);
        
        int x = 15;
        int y = 16;
        boolean legality = gameboard.isPieceLegal(testPiece, x, y);
        gameboard.placePiece(testPiece, x, y);
        gameboard.printBoard();
        System.out.println(legality);
        

        // System.out.println("P1 Score: " + gameboard.calcScoreP1(testPiece,
        // testPiece));
        // System.out.println("P2 Score: " + gameboard.calcScoreP2(testPiece,
        // testPiece));

        // testPiece.rotateLeft();
        // testPiece.rotateLeft();
        // testPiece.printLayout();
        // System.out.println(gamelogic.validFirstMove(testPiece, 0, 18));
        // testPiece.printLayout();
        // System.out.println(gamelogic.validFirstMove(testPiece, 17, 17));

    }
}