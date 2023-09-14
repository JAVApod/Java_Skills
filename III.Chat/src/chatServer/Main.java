package chatServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws IOException {
    // create a server socket
    ServerSocket server = new ServerSocket(1234);
    while (true) {
      System.out.println("Waiting...");

      // wait for a client to connect
      Socket socket = server.accept();
      System.out.println("Client connected!");

      // get input/output streams
      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();

      // read/write from/to console
      Scanner in = new Scanner(is);
      PrintStream out = new PrintStream(os);

      // read/write from/to network
      out.println("Welcome to mountains!");
      String input = in.nextLine();
      while (!input.equals("bye")) {
        out.println(input + "-" + input + "-" +
            input.substring(input.length() / 2) + "...");
        input = in.nextLine();
      }
      socket.close();
    }
  }
}
