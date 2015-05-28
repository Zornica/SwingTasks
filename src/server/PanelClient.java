package server;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zornitsa Petkova on 5/28/15.
 */
public class PanelClient extends JPanel {
  JTextField text;

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

    text = new JTextField();
    Font font = new Font("Helvetica", Font.PLAIN, 22);
    text.setFont(font);
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(text, gridBagConstraints);
  }
}
