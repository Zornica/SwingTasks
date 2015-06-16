package server;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * Created by Zornitsa Petkova on 5/28/15.
 */
public class ServerFrame extends JFrame {
  PanelServer panel;
  Server server;


  public ServerFrame() {
    setSize(200, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("ServerFrame");
    panel = new PanelServer();
    ServerMessageImpl message = new ServerMessageImpl();
    server = new Server(new MessageListener() {
      @Override
      public void newMessage(String message) {}}, message, 1099);

    add(panel);
    setVisible(true);

    server.start();

    panel.button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        server.stop();
        System.exit(0);
      }
    });
  }


  public static void main(String[] args) throws IOException {
    ServerFrame server = new ServerFrame();
  }
}
