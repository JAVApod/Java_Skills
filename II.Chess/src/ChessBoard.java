public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public void switchPlayer() {
        this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
    }


    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        // check if the input is valid (0-7)
        if (startLine < 0 || startColumn < 0 || endLine < 0 || endColumn < 0 || startLine > 7
                || startColumn > 7 || endLine > 7 || endColumn > 7) {
            System.out.println("Error: Out of board");
            return false;
        }

        // check if start and end positions are not the same
        if (startLine == endLine && startColumn == endColumn) {
            System.out.println("Error: Start and end positions cannot be the same");
            return false;
        }

        // check if there is a piece at the start position
        if (board[startLine][startColumn] == null) {
            System.out.println("Error: No piece at the start position");
            return false;
        }

        // check if it's the right player's turn (White or Black)
        if (!board[startLine][startColumn].getColor().equals(nowPlayer)) {
            System.out.println("Error: It's " + nowPlayer + "'s turn");
            return false;
        }

        // check if there is a piece of the same color at the end position
        if (board[endLine][endColumn] != null && board[startLine][startColumn].getColor()
                .equals(board[endLine][endColumn].getColor())) {
            System.out.println("Error: There is a piece of the same color at the end position");
            return false;
        }

        // check if the piece can move to the end position
        if (!board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine,
                endColumn))
            return false;

        // check if King or Rook has moved (for castling)
        if (board[startLine][startColumn].getSymbol().equals("K")
                || board[startLine][startColumn].getSymbol().equals("R")) {
            board[startLine][startColumn].check = false; // ! todo: remove this old line of code
            board[startLine][startColumn].hasMoved = true; // set true: piece has moved
        }

        board[endLine][endColumn] = board[startLine][startColumn]; // set piece to new cell
        board[startLine][startColumn] = null; // set null to previous cell

        // check if King is under attack after the move
        if (isKingInCheck()) {
            // Revert the move if the King is in check after the move
            System.out.println("Error: King cannot be in check after the move");
            board[startLine][startColumn] = board[endLine][endColumn]; // set piece to previous cell
            board[endLine][endColumn] = null; // set null to new cell
            return false;
        }

        this.switchPlayer(); // opponent turn
        return true;
    }


    public void printBoard() { // print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol()
                            + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    } // end of printBoard()

    // ** check if the field is under attack */
    boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        String opponentColor;

        // iterate through all cells on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];

                if (piece == null) {
                    continue; // skip this cell
                }

                opponentColor = this.nowPlayerColor().equals("White") ? "Black" : "White";
                if (!piece.getColor().equals(opponentColor)) {
                    continue; // skip this cell
                }

                if (piece.getSymbol().equals("P")) {
                    if (opponentColor.equals("White")) {
                        if (line == i + 1 && (column == j - 1 || column == j + 1))
                            return true; // The field is under attack
                    }
                    if (opponentColor.equals("Black")) {
                        if (line == i - 1 && (column == j - 1 || column == j + 1))
                            return true; // The field is under attack
                    }
                } else {
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true; // The field is under attack
                    }
                }

            } // end of for j
        } // end of for i

        return false; // The field is not under attack
    } // end of isUnderAttack


    // ** check if the player's King is under attack */
    public boolean isKingInCheck() {
        int kingLine = -1;
        int kingColumn = -1;

        // Find the King's position on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getSymbol().equals("K")
                        && board[i][j].getColor().equals(this.nowPlayerColor())) {
                    kingLine = i;
                    kingColumn = j;
                    break;
                }
            }
        }

        // Check if the King is under attack
        return isUnderAttack(this, kingLine, kingColumn);
    } // end of isKingInCheck

} // end of class ChessBoard
