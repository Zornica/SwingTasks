package multyserver;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public class ClientMessageImpl implements ClientMessage {
  private int count = 0;

  @Override
  public String connect() {
    count++;
    return "You are " + count + " client!";
  }

  @Override
  public String sendToAll(int count) {
    return "The Client " + count + "was connected!";
  }
}
