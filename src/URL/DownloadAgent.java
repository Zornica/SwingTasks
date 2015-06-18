package URL;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by Zornitsa Petkova on 5/25/15.
 */
public class DownloadAgent {


  PanelURL panel;

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
          byte[] data = new byte[1024];
          int downloadedFileSize = 0;
          int x = 0;
          while ((x = in.read(data)) >= 0) {
            downloadedFileSize += x;
            bout.write(data);
            float Percent = (downloadedFileSize * 100) / completeFileSize;
            System.out.println(Percent);
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

