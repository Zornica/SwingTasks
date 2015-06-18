package multyserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public class ClientThread extends Thread {
  private List<Socket> list;
  private Socket client;
  private ServerMessage serverMessage;
  private int count;
  private PrintWriter writer;

  public ClientThread(List<Socket> list, Socket client, ServerMessage serverMessage, int count) {
    this.list = list;
    this.client = client;
    this.serverMessage = serverMessage;
    this.count = count;
  }

  public void run() {

  }
}
