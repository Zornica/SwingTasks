package multyserver;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Zornitsa Petkova on 6/9/15.
 */
public class FrameServer extends JFrame implements MessageListener {

  PanelServer panel;
  Server server;


  public FrameServer() throws IOException {
    setSize(400, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("ServerFrame");
    panel = new PanelServer();
    ServerMessageImpl message = new ServerMessageImpl();

    server = new Server(message, this, 1430);

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
  public void newMessage(String message) {
    panel.text.append(message + "\n");
  }

  public static void main(String[] args) throws IOException {
    FrameServer frame = new FrameServer();
  }
}
