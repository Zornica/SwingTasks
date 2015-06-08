package examples.exaples.applet;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by Zornitsa Petkova on 6/3/15.
 */
public class HelloWorld extends Applet {
  public void paint(Graphics g) {
    g.drawString("Hello world!", 100, 100);
  }
}
