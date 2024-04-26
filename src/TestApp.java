public class TestApp {
    public static void main(String[] args) throws Exception {
        System.out.println("Testing");
        GameBoard gameboard = new GameBoard();
        GameLogic gamelogic = new GameLogic();
        Piece[] invBlue, invRed, invGreen, invYellow;

        invBlue = gameboard.createInvPieces(PieceColor.BLUE);
        invGreen = gameboard.createInvPieces(PieceColor.GREEN);
        invRed = gameboard.createInvPieces(PieceColor.RED);
        invYellow = gameboard.createInvPieces(PieceColor.YELLOW);

        Piece testPiece = new Piece(Piece.Type.TBLOCK, PieceColor.BLUE);
        testPiece.rotateRight();
        testPiece.printLayout();
        gameboard.tryPiece(testPiece, 18, 17);
        gameboard.placePiece(testPiece, 18, 17);

        int x = 16;
        int y = 16;
        // boolean legality = gameboard.isPieceLegal(testPiece, x, y);
        testPiece.rotateLeft();
        gameboard.placePiece(testPiece, x, y);
        // gameboard.printBoard();
        // System.out.println(legality);

        System.arraycopy(invBlue, 6, invBlue, 0, invBlue.length - 6);

        Move move = gamelogic.findMove(invBlue, gameboard);
        gameboard.placePiece(move.getPiece(), move.getX(), move.getY());
        gameboard.printBoard();

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