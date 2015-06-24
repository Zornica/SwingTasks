package multyserver;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Zornitsa Petkova on 6/18/15.
 */
public class ClientTest {
  private FakeServer server;
  private Client client;

  Mockery mockery = new JUnit4Mockery();

  @Rule
  public ExpectedException exception = ExpectedException.none();

  ServerMessage message= mockery.mock(ServerMessage.class);

  @Before
  public void setUp(){
    server = new FakeServer("You are client number: ",1090);
    FakeMessageListener messageListener = new FakeMessageListener();
    client = new Client(messageListener,1090);
  }

  @Test
  public void readMessage(){

    mockery.checking(new Expectations(){{
      oneOf(message).connectClient(server.message+server.count);
    }
    });

    new Thread(new Runnable() {
      @Override
      public void run() {
        server.start();
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        try{
        client.connect();
      }catch (NoSocketException nos){

        }
      }
    }).start();

    String message = client.getMessage();
    assertThat(message,is("You are client number: 1"));
  }

  @Test
  public void serverWasStopped(){
    mockery.checking(new Expectations(){{
    ignoring(message);
    }
    });

    new Thread(new Runnable() {
      @Override
      public void run() {
        server.start();
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        exception.expect(NoSocketException.class);
        try{
        client.connect();
        }catch (NoSocketException nos){

        }
      }
    }).start();

    server.stop();
  }
}
