package multyserver;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public class ServerMessageImpl implements ServerMessage {
  @Override
  public String connectClient(String message) {
    return message;
  }

  @Override
  public String sendToAll(String message) {
    return message;
  }

  @Override
  public String connect(String message) {

    return message;
  }

}
