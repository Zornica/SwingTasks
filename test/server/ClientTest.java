package server;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by Zornitsa Petkova on 5/29/15.
 */
public class ClientTest {
  private FakeMessageListener messageListener;
  private FakeServer server;
  private Client client;

  Mockery mockery = new JUnit4Mockery();

  ClientMessage clientMessage = mockery.mock(ClientMessage.class);

  @Before
  public void setUp() {
    messageListener = new FakeMessageListener();
    server = new FakeServer("Hello 2015 06 16", 1033);
    client = new Client(messageListener, clientMessage, 1033);
  }



  @Test
  public void isClientReceivedMessage() throws IOException {

    mockery.checking(new Expectations() {
      {
        ignoring(clientMessage);
      }
    });

    new Thread(new Runnable() {
      @Override
      public void run() {
        server.start();
      }
    }).start();
    client.connect();
    String receivedMessage = client.line;
    assertThat(receivedMessage, is("Hello 2015 06 16"));
    client.stop();

  }

  @Test
  public void isClientConnected() throws IOException {
    mockery.checking(new Expectations() {
      {
        oneOf(clientMessage).connect();
        will(returnValue("The client is connecting. "));
        oneOf(clientMessage).read();
        will(returnValue("The client reads from server."));
        oneOf(clientMessage).print();
        will(returnValue("The client prints the message from server."));
        oneOf(clientMessage).closeClient();
        will(returnValue("The client is closing."));
      }
    });

    new Thread(new Runnable() {
      @Override
      public void run() {
        server.start();
      }
    }).start();
    client.connect();

    StringBuilder list = new StringBuilder();
    list.append("The client is connecting. ");
    list.append("The client reads from server.");
    list.append("The client prints the message from server."+"\n"+client.line);
    list.append("The client is closing.");
    client.stop();
    assertThat(messageListener.listMessages.toString(),is(list.toString()));

  }


}
