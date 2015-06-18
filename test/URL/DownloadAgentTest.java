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
    downloadAgent.downloadFile("http://freedownloads.last.fm/download/492220550/Takyon%2B%2528Death%2BYon%2529.mp3","text.txt");
    downloadAgent.clear();
    assertThat((new File("text.txt").length()),is((long)2694970));
  }
}
