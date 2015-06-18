package URL;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Zornitsa Petkova on 5/25/15.
 */
public class Downloader extends JFrame implements ActionListener {
  PanelURL panel;
  DownloadAgent downloadAgent;


  public Downloader() {
    setSize(600, 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Downloader");
    panel = new PanelURL();
    downloadAgent = new DownloadAgent(panel);

    this.getContentPane().add(panel);
    setVisible(true);
    panel.download.addActionListener(this);
    panel.clear.addActionListener(this);
  }

  public void actionPerformed(final ActionEvent e) {


        new Thread(new Runnable() {
          @Override
          public void run() {
            if (((JButton) e.getSource()).getText().equals("Download")) {
              try {
                downloadAgent.downloadFile(panel.urlText.getText(), panel.fileText.getText());

              } catch (Exception ex) {
                System.out.println(ex.getMessage());
              }
            } else {
              downloadAgent.clear();
            }

          }
        }).start();

  }

}
