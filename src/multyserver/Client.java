package multyserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public class Client {
  private ClientMessage clientMessage;
  private ClientListener clientListener;
  private int port;
  private StringBuilder builder = new StringBuilder();
  private int count = 0;


  public Client(ClientMessage clientMessage, ClientListener clientListener, int port) {
    this.clientMessage = clientMessage;
    this.clientListener = clientListener;
    this.port = port;
  }

  public StringBuilder connect() throws NoSocketException {
    try {
      Socket client = new Socket("localhost", port);
      clientListener.newMessage(clientMessage.connect());
      BufferedReader buffer = new BufferedReader(new InputStreamReader(client.getInputStream()));
      String line;
      while (true) {
        line = buffer.readLine();
        builder.append(line);
        if (line == null) {
          client.close();
          throw new NoSocketException("Client stopped!");

        }
      }
    } catch (IOException ioe) {
      ioe.getStackTrace();
    }
    return builder;
  }
}


