package multyserver;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public class ServerMessageImpl implements ServerMessage {
  @Override
  public String connectClient(int count) {
    return "The client"+count+"is connecting!";
  }


}
