package rpn.parser;

import java.io.IOException;

/**
 * Created by german on 17.12.16.
 */
public interface State {
    State doAction(Tokenizer tokenizer) throws IOException;
}
