package multyserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Zornitsa Petkova on 6/9/15.
 */
public class FrameServer extends JFrame {

  PanelServer panel;
  Server server;


  public FrameServer() throws IOException {
    setSize(200, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("ServerFrame");
    panel = new PanelServer();
    server = new Server();

    add(panel);
    setVisible(true);


    panel.buttonStart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          server.start();
        } catch (IOException ioe) {
          ioe.getStackTrace();
        }
      }
    });

    panel.buttonStop.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          server.close();
        } catch (IOException ioe) {
          ioe.getStackTrace();
        }
      }
    });
  }


  public static void main(String[] args) throws IOException {
    FrameServer frame = new FrameServer();
  }
}
