package server;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * Created by Zornitsa Petkova on 5/28/15.
 */
public class Client extends JFrame implements ActionListener {
  PanelClient panel;
  ClientStart client;

  public Client() throws IOException {
    setSize(300, 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Client");
    panel = new PanelClient();
    client = new ClientStart();
    add(panel);
    setVisible(true);

    panel.button.addActionListener(this);

  }

  public void actionPerformed(ActionEvent e) {

    try {
      client.start();

      panel.text.append("The client connects with server!\n\n");

      panel.text.append("The client reads from server: \n" + client.s);


      panel.text.append("\n\nThe connection with server is closed!\n");
    } catch (IOException ex) {
      panel.text.append(ex.getMessage() + "\n");
    }

  }


  public static void main(String[] args) throws IOException {
    Client client = new Client();
  }
}
