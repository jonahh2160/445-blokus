public class TestApp {
    public static void main(String[] args) throws Exception {
        System.out.println("Testing");
        GameBoard gameBoard = new GameBoard();

        Piece testPiece = new Piece(Piece.Type.TBLOCK, PieceColor.BLUE);
        testPiece.printLayout();
        gameBoard.tryPiece(testPiece, 17, 18);
    }
}