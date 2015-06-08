package clientserver;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Ivan Kulekov (ivankulekov10@gmail.com)
 * @since May 22 , 2015 09:41
 */
public class ServerTest {
  private Server server;
  private MockServerMessageListener serverMessageListener;

  Clock clock = new Clock() {
    @Override
    public Date now() {
      return CalendarUtil.may(2015, 22);
    }
  };

  Mockery mockery = new JUnit4Mockery() {{
    setThreadingPolicy(new Synchroniser());
  }};

  ServerMessages serverMessages = mockery.mock(ServerMessages.class);

  @Before
  public void setUp() {
    serverMessageListener = new MockServerMessageListener();

    server = new Server(serverMessageListener, serverMessages, clock, 1048);
  }

  @After
  public void closeServer() {

    server.stopServer();
  }

  @Test
  public void sendMessageToClient() throws Exception {


    mockery.checking(new Expectations() {
      {
        oneOf(serverMessages).startServer();
        oneOf(serverMessages).acceptClient();
        oneOf(serverMessages).getHello();
        will(returnValue("Hello "));

        oneOf(serverMessages).sendMessage();
      }
    });

    server.startServer();

    Socket clientSocket = new Socket("localhost", 1048);

    String contentResponse = getContent(clientSocket);

    assertThat(contentResponse, is("Hello 2015-05-22"));

    clientSocket.close();
  }

  @Test
  public void newClientWasConnected() throws Exception {

    mockery.checking(new Expectations() {{
      oneOf(serverMessages).startServer();
      will(returnValue("Server starting on port 1048"));

      oneOf(serverMessages).acceptClient();
      will(returnValue("Server accept new client."));

      oneOf(serverMessages).getHello();
      will(returnValue("Hello"));

      oneOf(serverMessages).sendMessage();
      will(returnValue("Server send message to client"));
    }
    });

    server.startServer();

    Socket clientSocket = new Socket("localhost", 1048);

    assertThat(serverMessageListener.listMessages, hasItems("Server starting on port 1048", "Server accept new client.",
            "Server send message to client"));

    clientSocket.close();
  }


  private String getContent(Socket clientSocket) throws IOException {
    StringBuilder builder = new StringBuilder();

    InputStream inputStream = clientSocket.getInputStream();
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNext()) {
      builder.append(scanner.nextLine());
    }

    return builder.toString();
  }
}
