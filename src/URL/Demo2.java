package URL;

/**
 * Created by Zornitsa Petkova on 6/15/15.
 */
public class Demo2 {
  public static void main(String[] args)throws Exception{


  PanelURL panel = new PanelURL();
  DownloadAgent downloadAgent = new DownloadAgent(panel);
  downloadAgent.downloadFile("http://docs.oracle.com/javase/7/docs/api/java/util/Hashtable.html","text.txt");
    downloadAgent.clear();
    downloadAgent.downloadFile("http://docs.oracle.com/javase/7/docs/api/java/util/Hashtable.html","zuz.txt");
    downloadAgent.clear();
  }
}
