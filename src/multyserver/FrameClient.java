package multyserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Zornitsa Petkova on 6/9/15.
 */
public class FrameClient extends JFrame implements ActionListener{
  private PanelClient panel;
  private Client client;

  public FrameClient(){
    setSize(500, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Client");
    panel = new PanelClient();
    client = new Client(panel);

    add(panel);
    setVisible(true);


    panel.button.addActionListener(this);

  }

  public void actionPerformed(ActionEvent e){
    try{
      client.start();
    }catch(IOException ioe){
      panel.text.append(ioe.getMessage()+"\n");
    }
  }

  public static void main(String[] args) {
    FrameClient frame = new FrameClient();
  }
}
