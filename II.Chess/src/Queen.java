public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        // Check if the move is a valid pawn move
        if (!isValidQueenMove(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        // Check if the path is clear for the queen's move
        if (!isQueenPathClear(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        return true; // Queen can move to the destination cell
    }

    @Override
    String getColor() {
        return super.color;
    }

    @Override
    String getSymbol() {
        return "Q";
    }

    boolean isValidQueenMove(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        if (!super.isMoveDiagonal(line, column, toLine, toColumn)
                && !super.isMoveStraight(line, column, toLine, toColumn)) {
            return false;
        }

        return true;
    }

    boolean isQueenPathClear(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {
        int rowDirection = Integer.compare(toLine, line); // -1, 0, 1
        int colDirection = Integer.compare(toColumn, column); // -1, 0, 1

        int currentRow = line + rowDirection; // row + (-1 || 0 || 1)
        int currentCol = column + colDirection; // col + (-1 || 0 || 1)

        // Check if there is a piece on the way
        while (currentRow != toLine || currentCol != toColumn) {
            ChessPiece piece = chessBoard.board[currentRow][currentCol];
            if (piece != null) {
                return false;
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        return true;
    }

}
