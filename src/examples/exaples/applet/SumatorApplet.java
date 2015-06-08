package examples.exaples.applet;

/**
 * Created by Zornitsa Petkova on 6/4/15.
 */
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class SumatorApplet extends Applet {
  private TextField mNumber1Field = new TextField();
  private TextField mNumber2Field = new TextField();
  private Button mCalcButton = new Button();
  private TextField mSumField = new TextField();
  private Color mLastBackgroundColor;

  public void init() {
    this.setBackground(Color.black);

    // Set layout manager to null
    this.setLayout(null);

    // Create the first text field
    mNumber1Field.setBounds(new Rectangle(20, 50, 60, 25));
    mNumber1Field.setBackground(Color.white);
    this.add(mNumber1Field, null);

    // Create the second text field
    mNumber2Field.setBounds(new Rectangle(95, 50, 60, 25));
    mNumber2Field.setBackground(Color.white);
    this.add(mNumber2Field, null);

    // Create the "calculate sum" button
    mCalcButton.setBounds(new Rectangle(170, 50, 90, 25));
    mCalcButton.setBackground(Color.green);
    mCalcButton.addActionListener(
            new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                calcSum();
              }
            });
    mCalcButton.setLabel("calc sum");
    this.add(mCalcButton, null);

    // Create the result text field
    mSumField.setEditable(false);
    mSumField.setBackground(Color.gray);
    mSumField.setForeground(Color.white);
    mSumField.setBounds(new Rectangle(20, 85, 240, 25));
    this.add(mSumField, null);
  }

  public boolean mouseDown(Event aEvent, int aX, int aY) {
    mLastBackgroundColor = this.getBackground();
    this.setBackground(Color.red);
    return true;
  }

  public boolean mouseUp(Event aEvent, int aX, int aY) {
    this.setBackground(mLastBackgroundColor);
    return true;
  }

  private void calcSum() {
    try {
      long s1 = new Long(mNumber1Field.
              getText()).longValue();
      long s2 = new Long(mNumber2Field.
              getText()).longValue();
      mSumField.setText(s1 + " + " + s2 + " = " +
              (s1+s2));
    } catch (Exception ex) {
      mSumField.setText("Error!");
    }
  }

  public void paint(Graphics aGraphics) {
    super.paint(aGraphics);
    Font font = new Font("Dialog", Font.BOLD, 23);
    aGraphics.setFont(font);
    aGraphics.setColor(Color.gray);
    aGraphics.drawString("Test sumator applet", 20, 32);
    aGraphics.setColor(Color.white);
    aGraphics.drawString("Test sumator applet", 18, 30);
  }

  public static void main(String[] aArgs) {
    Frame frame = new Frame("Sumator");
    frame.setSize(280,160);
    SumatorApplet applet = new SumatorApplet();
    applet.init();
    frame.add(applet);
    frame.setVisible(true);
    applet.start();
  }
}

