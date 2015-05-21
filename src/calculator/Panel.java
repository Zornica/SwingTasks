package calculator;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Zornitsa Petkova on 5/20/15.
 */
public class Panel extends JPanel {
  public JTextField textResult;
  public List<Buttons> list;


  public Panel() {

    this.setLayout(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridheight = 1;
    gridBagConstraints.weightx = 50;
    gridBagConstraints.weighty = 100;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    gridBagConstraints.anchor = GridBagConstraints.CENTER;
    gridBagConstraints.fill = GridBagConstraints.BOTH;

    textResult = new JTextField(20);
    textResult.setHorizontalAlignment(JTextField.RIGHT);
    Font font = new Font("Helvetica", Font.PLAIN, 22);
    textResult.setFont(font);

    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    this.add(textResult, gridBagConstraints);

    list = new ArrayList<Buttons>();
    list.add(new Buttons(new JButton("1"), new Point(0, 1, 1)));
    list.add(new Buttons(new JButton("2"), new Point(1, 1, 1)));
    list.add(new Buttons(new JButton("3"), new Point(2, 1, 1)));
    list.add(new Buttons(new JButton("4"), new Point(0, 2, 1)));
    list.add(new Buttons(new JButton("5"), new Point(1, 2, 1)));
    list.add(new Buttons(new JButton("6"), new Point(2, 2, 1)));
    list.add(new Buttons(new JButton("7"), new Point(0, 3, 1)));
    list.add(new Buttons(new JButton("8"), new Point(1, 3, 1)));
    list.add(new Buttons(new JButton("9"), new Point(2, 3, 1)));
    list.add(new Buttons(new JButton("0"), new Point(3, 3, 2)));
    list.add(new Buttons(new JButton("."), new Point(4, 0, 1)));
    list.add(new Buttons(new JButton("C"), new Point(3, 0, 1)));
    list.add(new Buttons(new JButton("+"), new Point(3, 1, 1)));
    list.add(new Buttons(new JButton("-"), new Point(4, 1, 1)));
    list.add(new Buttons(new JButton("*"), new Point(3, 2, 1)));
    list.add(new Buttons(new JButton("/"), new Point(4, 2, 1)));
    list.add(new Buttons(new JButton("="), new Point(0, 4, 5)));

    for (Buttons btn : list) {
      gridBagConstraints.gridwidth = btn.point.width;
      gridBagConstraints.gridx = btn.point.x;
      gridBagConstraints.gridy = btn.point.y;
      btn.button.setFont(font);
      this.add(btn.button, gridBagConstraints);
    }

  }


}
