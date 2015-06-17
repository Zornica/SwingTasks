package multyserver;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Zornitsa Petkova on 6/9/15.
 */
public class FrameServer extends JFrame implements ServerListener {

  PanelServer panel;
  Server server;


  public FrameServer() throws IOException {
    setSize(400, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("ServerFrame");
    panel = new PanelServer();
    ServerMessageImpl message = new ServerMessageImpl();
    ClientMessageImpl clientMessage = new ClientMessageImpl();
    server = new Server(message, this, clientMessage, 1080);

    add(panel);
    setVisible(true);


    panel.buttonStart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            server.start();
          }
        }).start();

      }
    });

    panel.buttonStop.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        server.stop();
      }
    });
  }

  @Override
  public void newClient(String message) {
    panel.text.setText(panel.text.getText() + message + "\n");
  }

  public static void main(String[] args) throws IOException {
    FrameServer frame = new FrameServer();
  }
}
