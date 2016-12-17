package rpn;

import rpn.token.BraceToken;
import rpn.token.NumberToken;
import rpn.token.OperationToken;
import rpn.token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 17.12.16.
 */
public class TokenizerOld {
    private InputStream is;

    private int ch;

    public TokenizerOld(InputStream is) throws IOException {
        this.is = is;
        ch = is.read();
    }

    public List<Token> extractTokens() throws IOException {
        List<Token> tokens = new ArrayList<>();
        while (ch != -1) {
            if (Character.isSpaceChar(ch)) {
                continue;
            }
            if (ch == '(') {
                tokens.add(new BraceToken(BraceToken.Type.LEFT));
                ch = is.read();
            } else if (ch == ')') {
                tokens.add(new BraceToken(BraceToken.Type.RIGHT));
                ch = is.read();
            } else if (ch == '-') {
                tokens.add(new OperationToken(OperationToken.Operation.MINUS));
                ch = is.read();
            } else if (ch == '+') {
                tokens.add(new OperationToken(OperationToken.Operation.PLUS));
                ch = is.read();
            } else if (ch == '*') {
                tokens.add(new OperationToken(OperationToken.Operation.MULTIPLY));
                ch = is.read();
            } else if (ch == '/') {
                tokens.add(new OperationToken(OperationToken.Operation.DIVIDE));
                ch = is.read();
            } else if (Character.isDigit(ch)) {
                int number = 0;
                while (Character.isDigit(ch)) {
                    number = number * 10 + Character.digit(ch, 10);
                    ch = is.read();
                }
                tokens.add(new NumberToken(number));
            }
        }
        return tokens;
    }
}
