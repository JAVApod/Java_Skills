public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        if (!isValidKingMove(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        return true; // King can move to destination cell
    }

    @Override
    String getColor() {
        return super.color;
    }

    @Override
    String getSymbol() {
        return "K";
    }

    private boolean isValidKingMove(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        int deltaLine = Math.abs(toLine - line);
        int deltaColumn = Math.abs(toColumn - column);

        // Check if the move is one step in any direction (horizontal, vertical, or diagonal)
        if (deltaLine <= 1 && deltaColumn <= 1) {
            return true;
        }

        // Castling move check
        if (deltaLine == 0 && deltaColumn == 2) {
            if (toColumn > column) {
                // Castling with Rook on the right side (7 column)
                return chessBoard.castling7();
            } else {
                // Castling with Rook on the left side (0 column)
                return chessBoard.castling0();
            }
        }

        return false;

    }

}
