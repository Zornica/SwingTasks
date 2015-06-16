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
  public String line;
  Socket client;

  public Client(MessageListener clientListener, ClientMessage clientMessage, int port) {
    this.messageListener = clientListener;
    this.clientMessage = clientMessage;
    this.port = port;
  }

  public void connect() {
    try {
       client = new Socket("localhost", port);
      messageListener.newMessage(clientMessage.connect());
      BufferedReader buffer = new BufferedReader(new InputStreamReader(client.getInputStream()));
      line = buffer.readLine();
      messageListener.newMessage(clientMessage.read());
      messageListener.newMessage(clientMessage.print());
      messageListener.newMessage(line);


    } catch (IOException ioe) {
      ioe.getStackTrace();
    }
  }

  public void stop()throws IOException{
    client.close();
    messageListener.newMessage(clientMessage.closeClient());
  }

}
