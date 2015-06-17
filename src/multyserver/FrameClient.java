package multyserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Zornitsa Petkova on 6/9/15.
 */
public class FrameClient extends JFrame implements ClientListener {
  private PanelClient panel;
  private Client client;

  public FrameClient() {
    setSize(500, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("ClientFrame");
    panel = new PanelClient();
    ClientMessageImpl message = new ClientMessageImpl();
    client = new Client(message, this, 1080);

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
    panel.text.setText(panel.text.getText() + message + "\n");
  }


  public static void main(String[] args) {
    FrameClient frame = new FrameClient();
  }
}
