import java.math.BigDecimal;

public class Command {

    private Operator operator;
    private BigDecimal operand;

    public Command(Operator operator, BigDecimal operand) {
        this.operator = operator;
        this.operand = operand;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public BigDecimal getOperand() {
        return operand;
    }

    public void setOperand(BigDecimal operand) {
        this.operand = operand;
    }
}
