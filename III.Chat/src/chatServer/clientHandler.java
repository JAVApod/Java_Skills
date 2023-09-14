package chatServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class ClientHandler implements Runnable {
  Socket socket;

  public ClientHandler(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    try {
      // get input and output streams from the socket
      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();

      // create scanner and printstream objects for reading/writing data
      Scanner in = new Scanner(is);
      PrintStream out = new PrintStream(os);

      // read from client and write back
      out.println("Welcome to mountains!");
      String input = in.nextLine();
      while (!input.equals("bye")) {
        out.println(input + "-" + input + "-" +
            input.substring(input.length() / 2) + "...");
        input = in.nextLine();
      }
      socket.close();
      System.out.println("Client disconnected");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
