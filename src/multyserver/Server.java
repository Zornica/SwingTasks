package multyserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public class Server {
  public int count = 1;
  private ServerMessage serverMessage;
  private ServerListener serverListener;
  private int port;
  private ServerSocket server;
  private ClientMessage clientMessage;
  private ArrayList<Socket> list = new ArrayList<Socket>();

  public Server(ServerMessage serverMessage, ServerListener serverListener, ClientMessage clientMessage, int port) {
    this.serverMessage = serverMessage;
    this.serverListener = serverListener;
    this.clientMessage = clientMessage;
    this.port = port;
  }

  public void start() {
    Socket client = null;
    try {
      server = new ServerSocket(port);
      while (true) {
        client = server.accept();
        list.add(client);
        serverListener.newClient(serverMessage.connectClient(count));
        new ClientThread(list, client, serverMessage, clientMessage, count).start();

        Thread.sleep(200);
        count++;
      }
    } catch (IOException ioe) {
      ioe.getStackTrace();
    } catch (InterruptedException in) {
      in.getStackTrace();
    } finally {
      if (client != null) {
        try {
          client.close();
        } catch (IOException ioe) {
          ioe.getStackTrace();
        }
      }
    }
  }

  public void stop() {
    try {
      if (server != null) {
        server.close();
      }
    } catch (IOException ioe) {
      ioe.getStackTrace();
    }
  }
}
