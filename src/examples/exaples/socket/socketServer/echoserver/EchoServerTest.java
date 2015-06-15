package examples.exaples.socket.socketServer.echoserver;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


import org.testng.annotations.Test;

public class EchoServerTest {

  private static final String TEST_HOSTNAME = "localhost";
  private static final int TEST_PORT = 8192;
  EchoServer echoServer = null;



  @Test
  public void testClientConnects() {
    echoServer = new EchoServer();
    Socket testSocket = null;
    try {
      testSocket = new Socket(TEST_HOSTNAME, TEST_PORT);

      assertTrue(echoServer.clientConnect());
    } catch (Exception e) {
      assertTrue(false);
    }

    echoServer.serverDisconnect();
    try {
      testSocket.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testClientDisconnect() {
    echoServer = new EchoServer();
    Socket testSocket = null;
    try {
      testSocket = new Socket(TEST_HOSTNAME, TEST_PORT);
      echoServer.clientConnect();
      echoServer.clientDisconnect();
      Scanner testScanner = new Scanner(testSocket.getInputStream());
      testScanner.nextLine();
      assertTrue(false);
    } catch (Exception e) {
    }

    try {
      testSocket.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testServerDisconnect() {
    echoServer = new EchoServer();
    Socket testSocket = null;
    try {
      testSocket = new Socket(TEST_HOSTNAME, TEST_PORT);
      echoServer.clientConnect();
      echoServer.serverDisconnect();
      Scanner testScanner = new Scanner(testSocket.getInputStream());
      testScanner.nextLine();
      assertTrue(false);
    } catch (Exception e) {
    }

    try {
      testSocket.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testClientMessage() {
    echoServer = new EchoServer();
    Socket testSocket = null;
    String testMessage = "Test Message";
    try {
      testSocket = new Socket(TEST_HOSTNAME, TEST_PORT);
      echoServer.clientConnect();
      PrintWriter testWriter = new PrintWriter(
              testSocket.getOutputStream());
      testWriter.println(testMessage);
      String resultString = echoServer.getMessage();
      assertEquals(testMessage, resultString);
    } catch (Exception e) {
      /*assertTrue(false);*/
    }

    try {
      testSocket.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testClientMessageBye() {
    echoServer = new EchoServer();
    Socket testSocket = null;
    String testMessage = "Bye";
    try {
      testSocket = new Socket(TEST_HOSTNAME, TEST_PORT);
      echoServer.clientConnect();
      PrintWriter testWriter = new PrintWriter(
              testSocket.getOutputStream());
      testWriter.println(testMessage);
      echoServer.getMessage();
      Scanner testScanner = new Scanner(testSocket.getInputStream());
      testScanner.nextLine();
      assertTrue(false);
    } catch (Exception e) {
    }

    try {
      testSocket.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
