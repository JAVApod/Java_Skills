public abstract class ChessPiece {
    boolean check = true; // ! remove this old version of code. true = piece has not moved yet
    boolean hasMoved = false; // for castings
    final String color; // made color final since it should not change after initialization

    public ChessPiece(String color) {
        this.color = color;
    }

    abstract String getColor();

    abstract String getSymbol();

    abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine,
            int toColumn);

    boolean isMoveDiagonal(int line, int column, int toLine, int toColumn) {
        int deltaLine = Math.abs(toLine - line);
        int deltaColumn = Math.abs(toColumn - column);
        return deltaLine == deltaColumn;
    }

    boolean isMoveStraight(int line, int column, int toLine, int toColumn) {
        return (line == toLine || column == toColumn);
    }

}
