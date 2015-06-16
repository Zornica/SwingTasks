package server;

/**
 * Created by Zornitsa Petkova on 6/16/15.
 */
public class ClientMessageImpl implements ClientMessage {
  public String connect() {
    return "The client is connecting. ";
  }

  public String read() {
    return "The client reads from server.";
  }

  public String print() {
    return "The client prints the message from server.";
  }

  public String closeClient() {
    return " The client is closing.";
  }
}
