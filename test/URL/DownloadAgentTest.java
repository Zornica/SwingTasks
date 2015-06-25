package URL;

import org.junit.Test;

import java.io.File;


import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

/**
 * Created by Zornitsa Petkova on 5/28/15.
 */
public class DownloadAgentTest {
  @Test
  public void downloadFile() throws Exception {
    PanelURL panel = new PanelURL();
    DownloadAgent downloadAgent = new DownloadAgent(panel);
    downloadAgent.downloadFile("http://www.oracle.com","text.txt");
    downloadAgent.clear();
    downloadAgent.downloadFile("http://www.oracle.com","zuz.txt");
    downloadAgent.clear();
    assertThat((new File("text.txt").length()),is(new File("zuz.txt").length()));
  }

  @Test
  public void progressBar() throws Exception {
    PanelURL panel = new PanelURL();
    DownloadAgent downloadAgent = new DownloadAgent(panel);
    downloadAgent.downloadFile("https://docs.oracle.com/javase/7/docs/api/java/util/Hashtable.html","text.txt");
 //int arr[]={1,3,5,7,8,10,12,14,15,17,19,21,23,24,26,27,29,31,33,34,36,38,40,42,42,44,46,47,49,51,53,54,56,58,60,61,63,65,67,69,70,72,74,76,77,79,81,83,84,86,88,90,92,93,95,97,99,100};
for(int a=1;a<=100;a++){
    assertThat(downloadAgent.list[a],is(a));
}
  }
}
