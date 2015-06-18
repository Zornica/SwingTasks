package multyserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zornitsa Petkova on 6/18/15.
 */
public class FakeMessageListener implements MessageListener {
  public List<String> list = new ArrayList<String>();

  @Override
  public void newMessage(String message){
    list.add(message);
  }
}
