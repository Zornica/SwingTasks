package server;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Zornitsa Petkova on 5/29/15.
 */
public class ClientTest {
  @Test
  public void isConnect() throws IOException {
    Server server = new Server();

    Client client = new Client();

    assertThat(client.panel.text.getText(), is("The client connects with server!\n\n" + "The client reads from server: \n" + "Hallo " + new Date() + "\n\nThe connection with server is closed!\n"));
  }


}
