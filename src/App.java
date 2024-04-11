public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Testing");
        GameBoard gameBoard = new GameBoard();

        Piece testPiece = new Piece(Piece.Type.TBLOCK, PieceColor.BLUE);
        testPiece.printLayout();
        testPiece.rotateRight();
        testPiece.printLayout();
        testPiece.rotateLeft();
        testPiece.rotateLeft();
        testPiece.printLayout();
        testPiece.rotateLeft();
        testPiece.printLayout();
    }
}
