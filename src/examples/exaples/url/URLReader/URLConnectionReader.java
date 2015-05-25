package examples.exaples.url.URLReader;

/**
 * Created by Zornitsa Petkova on 5/25/15.
 */

import java.net.*;
import java.io.*;

//Chete direktno ot URL vryzkata
public class URLConnectionReader {
  public static void main(String[] args) throws Exception {
    URL oracle = new URL("http://www.oracle.com/");
    URLConnection yc = oracle.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(
            yc.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null)
      System.out.println(inputLine);
    in.close();
  }
}
