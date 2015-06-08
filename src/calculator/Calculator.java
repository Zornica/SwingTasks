package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Zornitsa Petkova on 5/19/15.
 */
public class Calculator extends JFrame implements ActionListener {


  Panel thePanel;
  NumberOfButton btn;

  public Calculator() {
    setSize(400, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Calculator");
    thePanel = new Panel();
    btn = new NumberOfButton(thePanel);

    this.getContentPane().add(thePanel);
    setVisible(true);
    for (Buttons btn : thePanel.list) {
      btn.button.addActionListener(this);
    }
  }

  public void actionPerformed(ActionEvent e) {

    if (btn.isNumber((JButton) e.getSource())) {
      btn.onNumberPressed(((JButton) e.getSource()).getText());
    } else if (btn.isOperation(((JButton) e.getSource()).getText())) {
      btn.onOperationPressed();
    } else if (btn.isClear((JButton) e.getSource())) {
      btn.onClear();
    } else {
      btn.onEqualPressed();
    }
  }
}
