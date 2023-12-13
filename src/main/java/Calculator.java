import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Calculator {

    private List<Command> commandList = new ArrayList<>();
    private int commandSize; // commandList里有效的command个数
    private int commandPos; // 当前插入的command位置


    public Calculator add(BigDecimal operand) {
        this.addCommand(new Command(Operator.ADD, operand));
        return this;
    }

    public Calculator sub(BigDecimal operand) {
        this.addCommand(new Command(Operator.SUB, operand));
        return this;
    }

    public Calculator mul(BigDecimal operand) {
        this.addCommand(new Command(Operator.MUL, operand));
        return this;
    }

    public Calculator div(BigDecimal operand) {
        if (operand.equals(BigDecimal.ZERO)) {
            throw new RuntimeException("divide by zero");
        }
        this.addCommand(new Command(Operator.DIV, operand));
        return this;
    }

    public Calculator undo() {
        this.commandPos--;
        return this;
    }

    public Calculator redo() {
        if (this.commandPos == this.commandSize) {
            throw new RuntimeException("redo only can do after undo");
        }
        this.commandPos++;
        return this;
    }


    private void addCommand(Command command) {
        this.commandList.add(this.commandPos, command);
        this.commandPos++;
        this.commandSize = this.commandPos;
    }

    /**
     * calc基于逆波兰表达式实现优先级四则运算
     *
     * @return 计算结果
     */
    public BigDecimal calc() {
        LinkedList<Object> objectList = convert();
        LinkedList<BigDecimal> res = new LinkedList<>();
        for (Object o : objectList) {
            if (o instanceof BigDecimal) {
                res.addLast((BigDecimal) o);
            } else {
                Operator operator = (Operator) o;
                BigDecimal right = res.pollLast();
                BigDecimal left = res.pollLast();
                switch (operator) {
                    case ADD:
                        res.addLast(left.add(right));
                        break;
                    case SUB:
                        res.addLast(left.subtract(right));
                        break;
                    case MUL:
                        res.addLast(left.multiply(right));
                        break;
                    case DIV:
                        res.addLast(left.divide(right, 2, RoundingMode.HALF_EVEN));
                        break;
                }
            }
        }
        return res.pollLast();
    }

    /**
     * 转换为逆波兰表达式
     * @return
     */
    private LinkedList<Object> convert() {
        LinkedList<Operator> operatorStack = new LinkedList<>();
        LinkedList<Object> res = new LinkedList<>();
        res.addLast(BigDecimal.ZERO);

        for (int i = 0; i < this.commandPos; i++) {
            Command command = this.commandList.get(i);
            while (operatorStack.size() > 0 &&  command.getOperator().getPriority() <= operatorStack.peek().getPriority()) {
                res.addLast(operatorStack.pollLast());
            }
            operatorStack.addLast(command.getOperator());
            res.addLast(command.getOperand());
        }
        while (operatorStack.size() > 0) {
            res.addLast(operatorStack.pollLast());
        }
        return res;
    }

}
