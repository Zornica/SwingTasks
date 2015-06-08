package task4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 6/5/15.
 */
public class Server implements Runnable {
  private ServerSocket server = null;
  private Thread thread = null;
  private ServerThread client;

  public Server(int port) {
    try {
      System.out.println("Binding to port" + port + ", please wait ...");
      server = new ServerSocket(port);
      System.out.println("Server started: " + server);
      start();
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
  }

  public void run() {
    while (thread != null) {
      try {
        System.out.println("|Waiting for a client ... ");
        addThread(server.accept());
      } catch (IOException ioe) {
        System.out.println("Accept error: " + ioe.getMessage());
      }
    }
  }

  public void addThread(Socket socket) {
    System.out.println("Client accept: " + socket);
    client = new ServerThread(this, socket);
    try {
      client.open();
      client.start();
    } catch (IOException ioe) {
      System.out.println("Error opening thread: " + ioe.getMessage());
    }
  }

  public void start() {
  }

  public void stop() {
  }

  public static void main(String[] args) {
 Server server = new Server(1099);
  }
}

