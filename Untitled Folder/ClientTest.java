package clientserver;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

/**
 * @author Ivan Kulekov (ivankulekov10@gmail.com)
 * @since May 22 , 2015 09:38
 */
public class ClientTest {
  private MockClientMessageListener clientMessageListener;
  private BeginnerServer beginnerServer;
  private Client client;

  Mockery mockery = new JUnit4Mockery() {{
    setThreadingPolicy(new Synchroniser());
  }};

  UserMessages userMessages = mockery.mock(UserMessages.class);


  @Before
  public void setUp() {
    clientMessageListener = new MockClientMessageListener();
    beginnerServer = new BeginnerServer(1098, "Hello 2015-22-05");
    client = new Client(clientMessageListener, userMessages, 1098);
  }

  @After
  public void closeServer() {
    beginnerServer.stop();
  }

  @Test
  public void clientReceiveWhatWasSendFromServer() throws Exception {

    mockery.checking(new Expectations() {{
      ignoring(userMessages);
    }
    });

    new Thread(new Runnable() {
      @Override
      public void run() {
        beginnerServer.startServer();
      }
    }).start();

    client.connectToServer();

    String response = client.getResponse();

    assertThat(response, is("Hello 2015-22-05"));

    client.stop();
  }

  @Test
  public void newClientWasConnected() throws Exception {

    mockery.checking(new Expectations() {{
      oneOf(userMessages).connectClient();
      will(returnValue("Client is connected to server on port 3333."));

      oneOf(userMessages).readFromServer();
      will(returnValue("Client read message from server."));

      oneOf(userMessages).printMessage();
      will(returnValue("Client print message on the console"));

      oneOf(userMessages).closeClient();
      will(returnValue("Client close connection with server."));
    }
    });

    new Thread(new Runnable() {
      @Override
      public void run() {
        beginnerServer.startServer();
      }
    }).start();

    client.connectToServer();

    assertThat(clientMessageListener.listMessages, hasItems("Client is connected to server on port 3333.", "Client read message from server.",
            "Client print message on the console", "Client close connection with server."));
  }
}
