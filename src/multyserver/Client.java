package multyserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 6/8/15.
 */
public class Client {
  private String s;

  public void start() throws IOException {
    Socket connection = new Socket("localhost", 1099);
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    s = in.readLine();
    in.close();
    System.out.println(s);
  }

  public static void main(String[] args) throws IOException {
    Client client = new Client();
    client.start();

  }
}
