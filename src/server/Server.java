package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Zornitsa Petkova on 6/16/15.
 */
public class Server {

  private MessageListener messageListener;
  private ServerMessage serverMessage;
  private int port;
  private ServerSocket server;

  public Server(MessageListener messageListener, ServerMessage serverMessage, int port) {
    this.messageListener = messageListener;
    this.serverMessage = serverMessage;
    this.port = port;
  }

  public void start() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          server = new ServerSocket(port);
          while (true) {
            Socket client = server.accept();
            OutputStream output = client.getOutputStream();
            output.write((serverMessage.getMessage() + " " + new Date()).getBytes());
            output.flush();
            client.close();
          }
        } catch (IOException ioe) {
          ioe.getStackTrace();
        }
      }
    }).start();

  }

  public void stop() {
    if (server != null) {
      try {
        server.close();
      } catch (IOException ioe) {
        ioe.getStackTrace();
      }
    }
  }

}
