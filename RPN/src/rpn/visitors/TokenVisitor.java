package rpn.visitors;

import rpn.token.BraceToken;
import rpn.token.NumberToken;
import rpn.token.OperationToken;

public interface TokenVisitor {
    void visit(NumberToken token);
    void visit(BraceToken token);
    void visit(OperationToken token);
}
