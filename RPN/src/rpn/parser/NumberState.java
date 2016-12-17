package rpn.parser;

import rpn.token.NumberToken;

import java.io.IOException;

/**
 * Created by german on 17.12.16.
 */
public class NumberState implements State {

    private int number;

    public NumberState(int number) {
        this.number = number;
    }

    @Override
    public State doAction(Tokenizer tokenizer) throws IOException {
        int ch = tokenizer.getCh();
        if (Character.isDigit(ch)) {
            tokenizer.getNextCh();
            return new NumberState(number * 10 + Character.digit(ch, 10));
        } else {
            tokenizer.addToken(new NumberToken(number));
            return new StartState();
        }
    }
}
