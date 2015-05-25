package URL;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * Created by Zornitsa Petkova on 5/25/15.
 */
public class DownloadAgent {

  String url;
  String outputFile;
  PanelURL panel;

  public DownloadAgent(PanelURL panel) {
    this.panel = panel;
  }

  public void downloadFile() throws Exception {
    url = panel.urlText.getText();
    outputFile = panel.fileText.getText();
    URL url1 = new URL(url);

    URLConnection file = url1.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()));
    int inputLine;
    OutputStream out = new FileOutputStream(outputFile);
    while ((inputLine = in.read()) != -1) {
      System.out.println(inputLine);
      out.write(inputLine);
     doInBackground();
    }
    in.close();
    out.close();
  }
  public void doInBackground() {
    Random random = new Random();
    int progress = 0;
    // Initialize progress property.
    panel.progressBar.setValue(0);
    while (progress < 100) {
      // Sleep for up to one second.
      try {
        Thread.sleep(random.nextInt(1000));
      } catch (InterruptedException ignore) {
      }
      // Make random progress.
      progress += random.nextInt(10);
      panel.progressBar.setValue(Math.min(progress, 100));
    }

  }

  public void clear() {
    panel.urlText.setText("");
    panel.fileText.setText("");
  }
}

