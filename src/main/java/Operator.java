public enum Operator {

    ADD(0),
    SUB(0),
    MUL(1),
    DIV(1);

    private int priority;


    Operator(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
