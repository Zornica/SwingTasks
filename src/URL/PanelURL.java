package URL;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zornitsa Petkova on 5/25/15.
 */
public class PanelURL extends JPanel {
  JLabel url;
  JLabel fileName;
  JTextField urlText;
  JTextField fileText;
  JButton download;
  JButton clear;
  JProgressBar progressBar;

  public PanelURL() {

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

    Font font = new Font("Helvetica", Font.PLAIN, 22);

    url = new JLabel("URL");
    url.setVerticalTextPosition(JLabel.BOTTOM);
    url.setHorizontalTextPosition(JLabel.CENTER);
    url.setFont(font);

    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    this.add(url, gridBagConstraints);

    fileName = new JLabel("File");
    fileName.setVerticalTextPosition(JLabel.BOTTOM);
    fileName.setHorizontalTextPosition(JLabel.CENTER);
    fileName.setFont(font);

    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    this.add(fileName, gridBagConstraints);

    urlText = new JTextField();
    urlText.setFont(font);
    gridBagConstraints.gridwidth = 4;
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    this.add(urlText, gridBagConstraints);

    fileText = new JTextField();
    fileText.setFont(font);
    gridBagConstraints.gridwidth = 4;
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    this.add(fileText, gridBagConstraints);

    progressBar = new JProgressBar(0, 100);
    progressBar.setValue(0);
    progressBar.setStringPainted(true);
    gridBagConstraints.gridwidth = 4;
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    this.add(progressBar, gridBagConstraints);

    download = new JButton("Download");
    download.setFont(font);
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    this.add(download, gridBagConstraints);

    clear = new JButton("Clear");
    clear.setFont(font);
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    this.add(clear, gridBagConstraints);

  }
}
