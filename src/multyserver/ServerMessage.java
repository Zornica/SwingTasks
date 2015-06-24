package multyserver;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public interface ServerMessage {
  String connectClient(String message);
  String sendToAll(String message);
  String connect(String message);
}
