package chess;

public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        // Check if the move is a valid pawn move
        if (!isValidPawnMove(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        // Check if the path is clear for the pawn's forward move
        if (!isPawnPathClear(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        return true; // Pawn can move to the destination cell
    }

    @Override
    String getColor() {
        return super.color;
    }

    @Override
    String getSymbol() {
        return "P";
    }

    private boolean isValidPawnMove(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        int direction = getColor().equals("White") ? 1 : -1;
        int startLine = getColor().equals("White") ? 1 : 6;
        int deltaColumn = Math.abs(toColumn - column);

        // Move one step forward
        if (toLine == line + direction && toColumn == column) {
            return true;
        }

        // Move two steps forward from the starting position
        if (line == startLine && toLine == line + (2 * direction) && toColumn == column) {
            return true;
        }

        // Capture opponent's piece diagonally
        if (toLine == line + direction && deltaColumn == 1) {
            ChessPiece destinationPiece = chessBoard.board[toLine][toColumn];
            if (destinationPiece != null && !destinationPiece.getColor().equals(getColor())) {
                return true;
            }
        }

        // todo: Handle en passant capture (not fully implemented)

        return false;
    }

    private boolean isPawnPathClear(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        // Check if the move is a straight forward move
        if (toColumn == column) {
            // Check if the destination cell is empty
            if (chessBoard.board[toLine][toColumn] != null) {
                return false;
            }

            // Check if the cell in front of the pawn is empty when moving two steps forward
            int direction = getColor().equals("White") ? 1 : -1;
            int deltaLine = toLine - line;
            if (deltaLine == 2 && chessBoard.board[line + direction][column] != null) {
                return false;
            }
        }

        return true;
    }

}
