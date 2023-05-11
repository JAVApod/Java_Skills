public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        // Check if the move is a valid bishop move
        if (!isValidBishopMove(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        // Check if the path is clear for the bishop's move
        if (!isBishopPathClear(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        return true; // Bishop can move to the destination cell
    }

    @Override
    String getColor() {
        return super.color;
    }

    @Override
    String getSymbol() {
        return "B";
    }

    private boolean isValidBishopMove(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        if (!super.isMoveDiagonal(line, column, toLine, toColumn)) {
            return false;
        }

        return true;
    }

    private boolean isBishopPathClear(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {
        int lineDirection = toLine > line ? 1 : -1;
        int columnDirection = toColumn > column ? 1 : -1;
        int deltaLine = Math.abs(toLine - line);

        for (int i = 1; i < deltaLine; i++) {
            if (chessBoard.board[line + i * lineDirection][column + i * columnDirection] != null) {
                return false;
            }
        }

        return true;
    }

}
