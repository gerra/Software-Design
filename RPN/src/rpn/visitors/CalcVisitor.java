package rpn.visitors;

import rpn.token.BraceToken;
import rpn.token.NumberToken;
import rpn.token.OperationToken;
import rpn.token.Token;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Created by german on 17.12.16.
 */
public class CalcVisitor implements TokenVisitor {

    private List<Token> rpnTokens;
    private Stack<NumberToken> numbers = new Stack<>();

    public CalcVisitor(List<Token> rpnTokens) {
        this.rpnTokens = rpnTokens;
    }

    @Override
    public void visit(NumberToken token) {
        numbers.add(token);
    }

    @Override
    public void visit(BraceToken token) {
        throw new IllegalStateException("We reached unreachable state, bit the programmer who had coded this");
    }

    @Override
    public void visit(OperationToken token) {
        try {
            NumberToken secondOperand = numbers.pop();
            NumberToken firstOperand = numbers.pop();
            int result = token.calc(firstOperand.getNumber(), secondOperand.getNumber());
            numbers.add(new NumberToken(result));
        } catch (EmptyStackException e) {
            throw new IllegalStateException("There are not enough operands for operation " + token.getOperation().name());
        }
    }

    public int calc() {
        for (Token npeToken : rpnTokens) {
            npeToken.accept(this);
        }
        int result = 0;
        if (numbers.size() == 1) {
            result = numbers.get(0).getNumber();
        }
        return result;
    }
}
