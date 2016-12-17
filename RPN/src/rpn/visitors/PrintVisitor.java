package rpn.visitors;

import rpn.token.BraceToken;
import rpn.token.NumberToken;
import rpn.token.OperationToken;
import rpn.token.Token;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by german on 17.12.16.
 */
public class PrintVisitor implements TokenVisitor {

    private List<Token> rpnTokens;
    private ByteArrayOutputStream outputStream;

    public PrintVisitor(List<Token> rpnTokens) {
        this.rpnTokens = rpnTokens;
    }

    @Override
    public void visit(NumberToken token) {
        try {
            outputStream.write((String.valueOf(token.getNumber()) + " ").getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void visit(BraceToken token) {
        try {
            outputStream.write((token.getType().name() + " ").getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void visit(OperationToken token) {
        try {
            outputStream.write((token.getOperation().name() + " ").getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public OutputStream getOutputStream() throws IOException {
        outputStream = new ByteArrayOutputStream();
        for (Token rpnToken : rpnTokens) {
            rpnToken.accept(this);
        }
        return outputStream;
    }
}
