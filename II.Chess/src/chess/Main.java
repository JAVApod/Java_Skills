package chess;

import java.util.Scanner;

public class Main {

  public static ChessBoard buildBoard() {
    ChessBoard board = new ChessBoard("White");

    board.board[0][0] = new Rook("White");
    board.board[0][1] = new Horse("White");
    board.board[0][2] = new Bishop("White");
    board.board[0][3] = new Queen("White");
    board.board[0][4] = new King("White");
    board.board[0][5] = new Bishop("White");
    board.board[0][6] = new Horse("White");
    board.board[0][7] = new Rook("White");
    board.board[1][0] = new Pawn("White");
    board.board[1][1] = new Pawn("White");
    board.board[1][2] = new Pawn("White");
    board.board[1][3] = new Pawn("White");
    board.board[1][4] = new Pawn("White");
    board.board[1][5] = new Pawn("White");
    board.board[1][6] = new Pawn("White");
    board.board[1][7] = new Pawn("White");

    board.board[7][0] = new Rook("Black");
    board.board[7][1] = new Horse("Black");
    board.board[7][2] = new Bishop("Black");
    board.board[7][3] = new Queen("Black");
    board.board[7][4] = new King("Black");
    board.board[7][5] = new Bishop("Black");
    board.board[7][6] = new Horse("Black");
    board.board[7][7] = new Rook("Black");
    board.board[6][0] = new Pawn("Black");
    board.board[6][1] = new Pawn("Black");
    board.board[6][2] = new Pawn("Black");
    board.board[6][3] = new Pawn("Black");
    board.board[6][4] = new Pawn("Black");
    board.board[6][5] = new Pawn("Black");
    board.board[6][6] = new Pawn("Black");
    board.board[6][7] = new Pawn("Black");
    return board;
  }

  public static void main(String[] args) {

    ChessBoard board = buildBoard();
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("""
          To check the game, you need to enter the commands:
          'exit' - to exit the game
          'replay' - to start the game again
          'castling0' or 'castling7' - to make castling
          'm' - to move the figure from the position (1, 1) to the position (2, 3)
          """);
      System.out.println();
      board.printBoard();
      while (true) {
        String s = scanner.nextLine();
        if (s.equals("exit"))
          break;
        else if (s.equals("replay")) {
          System.out.println("Start a new game");
          board = buildBoard();
          board.printBoard();
        } else {
          if (s.equals("castling0")) {
            if (board.castling0()) {
              System.out.println("Castling was successful");
              board.printBoard();
            } else {
              System.out.println("Castling failed");
            }
          } else if (s.equals("castling7")) {
            if (board.castling7()) {
              System.out.println("Castling was successful");
              board.printBoard();
            } else {
              System.out.println("Castling failed");
            }
          } else if (s.contains("move")) {
            String[] a = s.split(" ");
            try {
              int line = Integer.parseInt(a[1]);
              int column = Integer.parseInt(a[2]);
              int toLine = Integer.parseInt(a[3]);
              int toColumn = Integer.parseInt(a[4]);
              if (board.moveToPosition(line, column, toLine, toColumn)) {
                System.out.println("Move was successful");
                board.printBoard();
              } else
                System.out.println("Move failed");
            } catch (Exception e) {
              System.out.println("You entered an invalid move. Please try again.");
            }

          }
        }
      }
    }
  }
}
