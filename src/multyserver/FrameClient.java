package multyserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Zornitsa Petkova on 6/9/15.
 */
public class FrameClient extends JFrame implements MessageListener {
  private PanelClient panel;
  private Client client;

  public FrameClient() {
    setSize(500, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("ClientFrame");
    panel = new PanelClient();
    client = new Client(this, 1430);

    add(panel);
    setVisible(true);


    panel.button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              client.connect();
            } catch (NoSocketException nos) {
              panel.text.append(nos.getMessage() + "\n");
            }
          }
        }).start();
      }
    });

  }

  @Override
  public void newMessage(String message) {
    panel.text.append(message + "\n");
  }


  public static void main(String[] args) {
    FrameClient frame = new FrameClient();
  }
}
