package URL;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Zornitsa Petkova on 5/28/15.
 */
public class DownloadAgentTest {
  @Test
  public void downloadFile() throws Exception {
     Downloader downloader = new Downloader();
    DownloadAgent downloadAgent = new DownloadAgent(downloader.panel);
    downloader.panel.urlText.setText("http://zetcode.com/tutorials/javaswingtutorial/");
    downloader.panel.fileText.setText("zuz.txt");
    downloadAgent.downloadFile(downloader.panel.urlText.getText(),downloader.panel.fileText.getText());
    downloadAgent.clear();
    downloader.panel.urlText.setText("http://zetcode.com/tutorials/javaswingtutorial/");
    downloader.panel.fileText.setText("text.txt");
    downloadAgent.downloadFile(downloader.panel.urlText.getText(),downloader.panel.fileText.getText());
    assertThat(new File("zuz.txt").length(), is(new File("text.txt").length()));
  }
}
