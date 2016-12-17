package rpn.parser;

import rpn.token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 17.12.16.
 */
public class Tokenizer {
    private InputStream is;

    List<Token> tokens = new ArrayList<>();

    private int ch;

    public Tokenizer(InputStream is) throws IOException {
        this.is = is;
        ch = is.read();
    }

    public int getNextCh() throws IOException {
        return ch = is.read();
    }

    public int getCh() {
        return ch;
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public List<Token> extractTokens() throws IOException {
        State state = new StartState();
        while (!(state instanceof EndState)) {
            state = state.doAction(this);
        }
        if (state instanceof ErrorState) {
            throw new IllegalStateException("Bad string");
        }
        return tokens;
    }
}
