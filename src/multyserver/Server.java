package multyserver;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zornitsa Petkova on 6/8/15.
 */
public class Server {
  private int br = 0;
  private List<Socket> list;
  private Socket client;
  private ClientThread thread;

  public void start() throws IOException {
    ServerSocket serverSocket = new ServerSocket(1099);
    System.out.println("Server started");
    list = new ArrayList<Socket>();
    while (true) {
      client = serverSocket.accept();
      br++;
      thread = new ClientThread(client, br);
      thread.start();
      list.add(client);
      System.out.println("Thread started!");
      if (thread.isInterrupted()) {
        return;
      }
    }
  }

  public void close() throws IOException {
    client.close();
    thread.interrupt();

  }

  public static void main(String[] args) throws IOException {
    Server server = new Server();
    server.start();
    server.close();
  }
}
