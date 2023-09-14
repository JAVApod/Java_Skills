package chess;

public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        // check if the move is a valid knight move
        if (!isValidKnightMove(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        return true; // Knight can move to destination cell
    }

    private boolean isValidKnightMove(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        if (!isMoveShapeL(line, column, toLine, toColumn)) {
            return false;
        }

        return true;
    }

    @Override
    String getColor() {
        return super.color;
    }

    @Override
    String getSymbol() {
        return "N";
    }

    // knight moves "L" shape
    private boolean isMoveShapeL(int line, int column, int toLine, int toColumn) {
        int deltaLine = Math.abs(toLine - line);
        int deltaColumn = Math.abs(toColumn - column);
        return (deltaLine == 2 && deltaColumn == 1) || (deltaLine == 1 && deltaColumn == 2);
    }

}
