package calculator;


import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Zornitsa Petkova on 5/25/15.
 */
public class CalculatorTest {
  Calculator calc = new Calculator();
  NumberOfButton btn = new NumberOfButton(calc.thePanel);

  @Test
  public void calculatePLus() {
    btn.onNumberPressed("4");
    btn.isOperation("+");
    btn.onOperationPressed();
    btn.onNumberPressed("5");
    btn.onEqualPressed();
    assertThat(calc.thePanel.textResult.getText(), is("9.0"));
  }

  @Test
  public void calculateMinus() {
    btn.onNumberPressed("14");
    btn.isOperation("-");
    btn.onOperationPressed();
    btn.onNumberPressed("5");
    btn.onEqualPressed();
    assertThat(calc.thePanel.textResult.getText(), is("9.0"));
  }

  @Test
  public void calculateMultiply() {
    btn.onNumberPressed("4");
    btn.isOperation("*");
    btn.onOperationPressed();
    btn.onNumberPressed("5");
    btn.onEqualPressed();
    assertThat(calc.thePanel.textResult.getText(), is("20.0"));
  }

  @Test
  public void calculatePartition() {
    btn.onNumberPressed("20");
    btn.isOperation("/");
    btn.onOperationPressed();
    btn.onNumberPressed("5");
    btn.onEqualPressed();
    assertThat(calc.thePanel.textResult.getText(), is("4.0"));
  }

  @Test
  public void calculateExceptionPoint() {
    btn.onNumberPressed("2");
    btn.onNumberPressed(".");
    btn.onNumberPressed(".");
    btn.isOperation("/");
    btn.onOperationPressed();
    assertThat(calc.thePanel.textResult.getText(), is("Error: multiple points"));
  }
}
