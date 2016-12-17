package rpn.parser;

import java.io.IOException;

/**
 * Created by german on 17.12.16.
 */
public class EndState implements State {
    @Override
    public State doAction(Tokenizer tokenizer) throws IOException {
        return this;
    }
}
