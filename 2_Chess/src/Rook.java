public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn) {

        // Check if the move is a valid rook move
        if (!isValidRookMove(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        // Check if the path is clear for the rook's move
        if (!isRookPathClear(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        return true; // Rook can move to the destination cell
    }

    @Override
    String getColor() {
        return super.color;
    }

    @Override
    String getSymbol() {
        return "R";
    }

    boolean isValidRookMove(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (!super.isMoveStraight(line, column, toLine, toColumn)) {
            return false;
        }

        return true;
    }

    boolean isRookPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine) { // Horizontal move
            int direction = toColumn > column ? 1 : -1;
            for (int c = column + direction; c != toColumn; c += direction) {
                if (chessBoard.board[line][c] != null) {
                    return false; // Path is not clear
                }
            }
        } else { // Vertical move
            int direction = toLine > line ? 1 : -1;
            for (int l = line + direction; l != toLine; l += direction) {
                if (chessBoard.board[l][column] != null) {
                    return false; // Path is not clear
                }
            }
        }
        return true; // Path is clear
    }

}
