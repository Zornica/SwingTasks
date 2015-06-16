package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Zornitsa Petkova on 6/16/15.
 */
public class FakeServer {
  private int port;
  private String message;
  private ServerSocket server;

  public FakeServer( String message,int port) {
    this.port = port;
    this.message = message;
  }

  public void start() {
    try {
      server = new ServerSocket(port);
      while (true) {
        Socket client = server.accept();
        OutputStream output = client.getOutputStream();
        output.write(message.getBytes());
        output.flush();
        client.close();
      }
    } catch (IOException ioe) {
      ioe.getStackTrace();
    }
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
