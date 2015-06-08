package calculator;

import javax.swing.*;


/**
 * Created by Zornitsa Petkova on 5/19/15.
 */
public class NumberOfButton {
  Panel thePanel;
  public int function;
  public int operand;
  public Double number;
  public Double number2;
  public Double result;

  public NumberOfButton(Panel thePanel) {
    this.thePanel = thePanel;
  }

  public boolean isNumber(JButton button) {
    if (button.getText().equals("0") || button.getText().equals("1") || button.getText().equals("2")
            || button.getText().equals("3") || button.getText().equals("4") || button.getText().equals("5")
            || button.getText().equals("6") || button.getText().equals("7") || button.getText().equals("8")
            || button.getText().equals("9") || button.getText().equals(".")) {
      return true;
    }
    return false;
  }

  public boolean isOperation(String text) {
    if (text.equals("+")) {
      operand = 1;
      return true;
    } else {
      if (text.equals("-")) {
        operand = 2;
        return true;
      } else if (text.equals("*")) {
        operand = 3;
        return true;
      } else if (text.equals("/")) {
        operand = 4;
        return true;
      }
    }
    return false;
  }

  public boolean isClear(JButton button) {
    if (button.getText().equals("C")) {
      return true;
    }
    return false;
  }

  public void onNumberPressed(String b) {
    thePanel.textResult.setText(thePanel.textResult.getText() + b);
  }

  public void onOperationPressed() {
    number = Double.parseDouble(thePanel.textResult.getText());
    switch (operand) {
      case 1:
        function = 1;
        break;
      case 2:
        function = 2;
        break;
      case 3:
        function = 3;
        break;
      case 4:
        function = 4;
        break;
      default:
        break;
    }
    onClear();

  }

  public void onEqualPressed() {
    switch (function) {
      case 1:
        number2 = Double.parseDouble(thePanel.textResult.getText());
        result = number + number2;

        System.out.println(result.toString());
        thePanel.textResult.setText(result.toString());
        break;
      case 2:
        number = number - Double.parseDouble(thePanel.textResult.getText());
        thePanel.textResult.setText(number.toString());
        break;
      case 3:
        number = number * Double.parseDouble(thePanel.textResult.getText());
        thePanel.textResult.setText(number.toString());
        break;
      case 4:
        number = number / Double.parseDouble(thePanel.textResult.getText());
        thePanel.textResult.setText(number.toString());
        break;
      default:
        break;
    }
    number = 0.0;
  }

  public void onClear() {
    thePanel.textResult.setText("");
  }


}
