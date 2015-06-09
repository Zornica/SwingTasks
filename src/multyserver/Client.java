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
  private BufferedReader in;
  private PanelClient panel;

  public Client(PanelClient panel){
    this.panel = panel;
  }

  public void start() throws IOException {
    Socket connection = new Socket("localhost", 1099);
    in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    while (true){
      s = in.readLine();

      if(s == null){
        return;
      }
      else{
        System.out.println(s);
        panel.text.append(s + "\n");
      }
    }


  }

  public static void main(String[] args) throws IOException {
    Client client = new Client(new PanelClient());
    client.start();

  }
}
