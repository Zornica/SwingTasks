package multyserver;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public interface ServerMessage {
  String connectClient(int count);
  String sendToAll(int count);
  String connect(int count);
}
