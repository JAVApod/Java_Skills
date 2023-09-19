package chatServer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class ClientHandler implements Runnable {
  Socket socket;
  PrintStream out;

  public ClientHandler(Socket socket) {
    this.socket = socket;
    try {
      // create printstream object for writing data
      this.out = new PrintStream(socket.getOutputStream());
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void run() {
    try {
      // get input and output streams from the socket
      Scanner in = new Scanner(socket.getInputStream());
      out.println("Welcome to the chat server!"); // send welcome message

      String input = in.nextLine(); // read from client
      while (!input.equals("bye")) {
        out.println("You said: " + input); // write back to client
        for (ClientHandler client : MainServer.clients) {
          if (client != this) { // write to all clients except this one
            client.out.println("Client_" + MainServer.clients.indexOf(this) + " said: " + input);
          }

        }
        input = in.nextLine(); // read from client
      }
      socket.close();
      System.out.println("Client disconnected");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
