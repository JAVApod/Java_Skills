package chatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

  public static void main(String[] args) throws IOException {
    // create server socket
    ServerSocket server = new ServerSocket(1234);

    while (true) {
      // wait for client to connect
      System.out.println("Waiting...");
      Socket socket = server.accept();
      System.out.println("Client connected!");

      // create client handler and start thread
      ClientHandler client = new ClientHandler(socket);
      Thread thread = new Thread(client);
      thread.start();
    }

  } // end main

}
