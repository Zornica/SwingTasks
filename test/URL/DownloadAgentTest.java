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
    PanelURL panel = new PanelURL();
    DownloadAgent downloadAgent = new DownloadAgent(panel);
    downloadAgent.downloadFile("http://docs.oracle.com/javase/7/docs/api/java/util/Hashtable.html","text.txt");
    downloadAgent.clear();
    downloadAgent.downloadFile("http://docs.oracle.com/javase/7/docs/api/java/util/Hashtable.html","zuz.txt");
    downloadAgent.clear();
    assertThat(new File("zuz.txt").length(), is(new File("text.txt").length()));
  }
}
