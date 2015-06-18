package multyserver;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public class ServerMessageImpl implements ServerMessage {
  @Override
  public String connectClient(int count) {
    return "The client" + count + "is connecting!";
  }

  @Override
  public String sendToAll(int count) {
    return "The Client " + count + "was connected!";
  }

  @Override
  public String connect(int count) {

    return "You are client!";
  }

}
