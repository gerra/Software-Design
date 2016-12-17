package rpn.token;

import rpn.visitors.TokenVisitor;

/**
 * Created by german on 17.12.16.
 */
public class OperationToken implements Token, Comparable {

    public enum Operation {
        PLUS(1), MINUS(1), MULTIPLY(2), DIVIDE(2);

        private int priority;

        Operation(int priority) {
            this.priority = priority;
        }
    }

    private Operation operation;

    public OperationToken(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public int calc(int operand1, int operand2) {
        switch (operation) {
            case PLUS:
                return operand1 + operand2;
            case MINUS:
                return operand1 - operand2;
            case MULTIPLY:
                return operand1 * operand2;
            case DIVIDE:
                return operand1 / operand2;
            default:
                return 0;
        }
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof OperationToken) {
            return operation.priority - ((OperationToken) o).operation.priority;
        } else {
            return 1;
        }
    }

}
