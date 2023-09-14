package chatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

  static List<ClientHandler> clients = new ArrayList<>();

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
      clients.add(client); // add client to list
      Thread thread = new Thread(client);
      thread.start();
    }

  } // end main

}
