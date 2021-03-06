package URL;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


/**
 * Created by Zornitsa Petkova on 5/25/15.
 */
public class DownloadAgent {

  float Percent = 0;
  PanelURL panel;
  int[] list = new int[1000];

  public DownloadAgent(PanelURL panel) {
    this.panel = panel;
  }

  public void downloadFile(final String url, final String outputFile) throws Exception {
    try {

      URL url1 = new URL(url);

      URLConnection file = url1.openConnection();
      int completeFileSize = file.getContentLength();
      System.out.println(completeFileSize);
      BufferedInputStream in = new BufferedInputStream(file.getInputStream());
      OutputStream out = new FileOutputStream(outputFile);
      BufferedOutputStream bout = new BufferedOutputStream(out);
      int downloadedFileSize = 0;
      int x = 0;
      while ((x = in.read()) != -1) {
        downloadedFileSize += 1;
        bout.write(x);
        float newPercent = (downloadedFileSize * 100) / completeFileSize;
        if (newPercent > Percent) {
          System.out.println(newPercent);
          Percent = newPercent;
          list[(int) Percent] = (int) Percent;
        }
        panel.progressBar.setValue((int) Percent);
      }
      in.close();
      out.close();
    } catch (IOException e) {

      e.getMessage();

    }
  }


  public void clear() {
    panel.urlText.setText("");
    panel.fileText.setText("");
    panel.progressBar.setValue(0);
  }

}

