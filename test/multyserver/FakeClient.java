package multyserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 6/18/15.
 */
public class FakeClient {
  private StringBuilder builder = new StringBuilder();
  private int port;

  public FakeClient(int port) {
    this.port = port;
  }

  public void connect() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        Socket client = null;
        try {
          while (true) {
            try {
              client = new Socket("localhost", port);
              break;
            } catch (IOException ioe) {
              ioe.getStackTrace();
            }
          }
          BufferedReader buffer = new BufferedReader(new InputStreamReader(client.getInputStream()));
          String line;
          while ((line = buffer.readLine()) != null) {
            builder.append(line);
          }
        } catch (IOException ioe) {
          ioe.getStackTrace();
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
    }).start();
  }

  public String getMessage() {
    return builder.toString();
  }
}
