import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CalculatorTest {

    @Test
    public void calc() {
        Calculator calculator = new Calculator();
        BigDecimal res = calculator.add(new BigDecimal("1.1"))
                .sub(new BigDecimal("2"))
                .mul(new BigDecimal("3"))
                .add(new BigDecimal("1"))
                .div(new BigDecimal("1"))
                .calc();
        Assert.assertEquals(new BigDecimal("-3.9").compareTo(res), 0);
    }

    @Test
    public void undo() {
        Calculator calculator = new Calculator();
        BigDecimal res = calculator.add(new BigDecimal("1.1"))
                .sub(new BigDecimal("2"))
                .mul(new BigDecimal("3"))
                .add(new BigDecimal("1"))
                .div(new BigDecimal("10"))
                .undo()
                .calc();
        Assert.assertEquals(new BigDecimal("-3.9").compareTo(res), 0);
    }

    @Test
    public void redo() {
        Assert.assertThrows(RuntimeException.class, ()-> {
            Calculator calculator = new Calculator();
            calculator.add(new BigDecimal("1.1"))
                    .sub(new BigDecimal("2"))
                    .mul(new BigDecimal("3"))
                    .add(new BigDecimal("1"))
                    .div(new BigDecimal("1"))
                    .redo()
                    .calc();
        });
    }
}