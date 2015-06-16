package server;


import javax.swing.*;
import java.io.IOException;


/**
 * Created by Zornitsa Petkova on 5/28/15.
 */
public class ClientFrame extends JFrame implements MessageListener {
  PanelClient panel;
  Client client;

  public ClientFrame() {
    setSize(400, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("ClientFrame");
    panel = new PanelClient();
    ClientMessage message = new ClientMessageImpl();
    client = new Client(this, message, 1099);
    add(panel);
    setVisible(true);
    client.connect();


  }

  public void newMessage(String message) {
    panel.text.append(message + "\n");
  }


  public static void main(String[] args) throws IOException {
    ClientFrame client = new ClientFrame();
  }
}
