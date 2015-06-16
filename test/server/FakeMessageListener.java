package server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zornitsa Petkova on 6/16/15.
 */
public class FakeMessageListener implements MessageListener {
  public StringBuilder listMessages = new StringBuilder();

  public void newMessage(String message) {

    listMessages.append(message);
  }
}
