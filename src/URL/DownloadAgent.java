package URL;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;


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
    Runnable updatethread = new Runnable() {
      public void run() {
        try {
          url = panel.urlText.getText();
          outputFile = panel.fileText.getText();
          URL url1 = new URL(url);

          URLConnection file = url1.openConnection();
          long completeFileSize = file.getContentLength();
          BufferedInputStream in = new BufferedInputStream(file.getInputStream());
          OutputStream out = new FileOutputStream(outputFile);
          BufferedOutputStream bout = new BufferedOutputStream(out, 1024);
          byte[] data = new byte[1024];
          int downloadedFileSize = 0;
          int x = 0;

          while ((x = in.read(data, 0, 1024)) >= 0) {

            downloadedFileSize += x;
            bout.write(data, 0, x);
            // calculate progress
            float Percent = (downloadedFileSize * 100) / completeFileSize;
            System.out.println(Percent);
            panel.progressBar.setValue((int) Percent);
          }
          in.close();
          out.close();
        }
         catch (IOException e) {
           JOptionPane.showConfirmDialog((Component)
                   null,e.getMessage(), "Error",
                   JOptionPane.DEFAULT_OPTION);

        }
      }
    };
    new Thread(updatethread).start();

  }

  public void clear() {
    panel.urlText.setText("");
    panel.fileText.setText("");
    panel.progressBar.setValue(0);
  }

}

