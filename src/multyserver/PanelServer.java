package multyserver;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Zornitsa Petkova on 6/9/15.
 */
public class PanelServer extends JPanel {


  private GridBagConstraints gridBagConstraints;
  public JButton buttonStart;
  public JButton buttonStop;

  public PanelServer() {
    this.setLayout(new GridBagLayout());
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridheight = 1;
    gridBagConstraints.weightx = 50;
    gridBagConstraints.weighty = 100;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    gridBagConstraints.anchor = GridBagConstraints.CENTER;
    gridBagConstraints.fill = GridBagConstraints.BOTH;


    Font font = new Font("Helvetica", Font.PLAIN, 22);
    buttonStart = new JButton("Start server");
    buttonStart.setFont(font);
    paint(1, 1, 3);
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    this.add(buttonStart, gridBagConstraints);


    buttonStop = new JButton("Stop server");
    buttonStop.setFont(font);
    paint(1, 2, 3);
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    this.add(buttonStop, gridBagConstraints);
  }

  public void paint(int x, int y, int width) {
    gridBagConstraints.gridx = x;
    gridBagConstraints.gridy = y;
    gridBagConstraints.gridwidth = width;

  }
}
