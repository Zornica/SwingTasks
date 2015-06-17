package multyserver;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public interface ClientMessage {
  String connect();
  String sendToAll(int count);
}
