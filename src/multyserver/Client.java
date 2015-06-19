package multyserver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public class Client {

  private MessageListener messageListener;
  private int port;
  private StringBuilder builder = new StringBuilder();
  String line;




  public Client(MessageListener messageListener,int port) {
    this.messageListener = messageListener;
    this.port = port;
  }

  public void connect() throws NoSocketException {
    try {
      Socket client = new Socket("localhost", port);
     InputStream in = client.getInputStream();
      byte[] buffer = new byte[1024];
      int read;
      while (true) {
        System.out.println("bedjc");
        read = in.read(buffer);
        line = new String(buffer , 0 , read -1 );
        System.out.println(line);
        builder.append(line);
        System.out.println(getMessage());
        messageListener.newMessage(line);
        if (line == null) {
          System.out.println("vbhilo ");
          client.close();
          throw new NoSocketException("Server stopped!");
        }
        System.out.println("aaaaa");
      }
    } catch (IOException ioe) {
      ioe.getStackTrace();
    }
  }

  public String getMessage(){
    return builder.toString();
  }
}


