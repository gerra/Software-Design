package rpn.parser;

import rpn.token.BraceToken;
import rpn.token.OperationToken;

import java.io.IOException;

/**
 * Created by german on 17.12.16.
 */
public class StartState implements State {
    @Override
    public State doAction(Tokenizer tokenizer) throws IOException {
        int ch = tokenizer.getCh();
        State nextState = this;
        boolean getNextChFlag = true;
        if (Character.isDigit(ch)) {
            nextState = new NumberState(Character.digit(ch, 10));
        } else if (ch == '*') {
            tokenizer.addToken(new OperationToken(OperationToken.Operation.MULTIPLY));
        } else if (ch == '/') {
            tokenizer.addToken(new OperationToken(OperationToken.Operation.DIVIDE));
        } else if (ch == '+') {
            tokenizer.addToken(new OperationToken(OperationToken.Operation.PLUS));
        } else if (ch == '-') {
            tokenizer.addToken(new OperationToken(OperationToken.Operation.MINUS));
        } else if (ch == '(') {
            tokenizer.addToken(new BraceToken(BraceToken.Type.LEFT));
        } else if (ch == ')') {
            tokenizer.addToken(new BraceToken(BraceToken.Type.RIGHT));
        } else if (ch == -1) {
            nextState = new EndState();
            getNextChFlag = false;
        } else if (!Character.isSpaceChar(ch)) {
            nextState = new ErrorState();
            getNextChFlag = false;
        }
        if (getNextChFlag) {
            tokenizer.getNextCh();
        }
        return nextState;
    }
}
