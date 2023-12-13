import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
public class Command {

    @Getter
    @Setter
    private Operator operator;

    @Getter
    @Setter
    private BigDecimal operand;

}
