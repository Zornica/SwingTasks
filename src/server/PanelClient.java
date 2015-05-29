package server;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zornitsa Petkova on 5/28/15.
 */
public class PanelClient extends JPanel {
  JTextArea text;
  JButton button;

  public PanelClient() {
    this.setLayout(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridheight = 1;
    gridBagConstraints.weightx = 50;
    gridBagConstraints.weighty = 100;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    gridBagConstraints.anchor = GridBagConstraints.CENTER;
    gridBagConstraints.fill = GridBagConstraints.BOTH;

    text = new JTextArea();
    Font font = new Font("Helvetica", Font.PLAIN, 14);
    text.setFont(font);
    gridBagConstraints.gridwidth = 8;
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    this.add(text, gridBagConstraints);

    button = new JButton("Start");
    Font font1 = new Font("Helvetica", Font.PLAIN, 32);
   button.setFont(font1);
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    this.add(button, gridBagConstraints);
  }
}
