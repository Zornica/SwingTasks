package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 6/16/15.
 */
public class Client {

  private ClientMessage clientMessage;
  private int port;
  private MessageListener messageListener;
  private StringBuilder builder;

  public Client(MessageListener clientListener, ClientMessage clientMessage, int port) {
    this.messageListener = clientListener;
    this.clientMessage = clientMessage;
    this.port = port;
  }

  public void connect() {
    try {
      Socket client = new Socket("localhost", port);
      messageListener.newMessage(clientMessage.read());
      BufferedReader buffer = new BufferedReader(new InputStreamReader(client.getInputStream()));
      String line;
      builder = new StringBuilder();
      line = buffer.readLine();
      messageListener.newMessage(clientMessage.read());
      System.out.println(line);
      builder.append(line);
      messageListener.newMessage(clientMessage.print() + "\n" + builder.toString());

      client.close();
      messageListener.newMessage(clientMessage.closeClient());

    } catch (IOException ioe) {
      ioe.getStackTrace();
    }
  }

  public void stop() {

  }

}
