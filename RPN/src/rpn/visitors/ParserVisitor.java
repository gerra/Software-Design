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
public class ParserVisitor implements TokenVisitor {

    private List<Token> infixTokens;
    private Stack<Token> stack = new Stack<>();
    private Stack<Token> result = new Stack<>();

    public ParserVisitor(List<Token> infixTokens) {
        this.infixTokens = infixTokens;
    }

    @Override
    public void visit(NumberToken token) {
        result.add(token);
    }

    @Override
    public void visit(BraceToken token) {
        if (token.getType() == BraceToken.Type.LEFT) {
            stack.add(token);
        } else {
            Token topToken;
            try {
                while (!((topToken = stack.pop()) instanceof BraceToken)) {
                    result.add(topToken);
                }
            } catch (EmptyStackException e) {
                throw new IllegalStateException("There are no \'(\' for \')\'");
            }
        }
    }

    @Override
    public void visit(OperationToken token) {
        if (stack.isEmpty()) {
            stack.add(token);
         } else {
            while (!stack.isEmpty() && token.compareTo(stack.peek()) <= 0) {
                result.add(stack.pop());
            }
            stack.add(token);
        }

    }

    public List<Token> convertToRPNTokens() {
        for (Token token : infixTokens) {
            token.accept(this);
        }
        while (!stack.isEmpty()) {
            Token topToken = stack.pop();
            if (topToken instanceof BraceToken) {
                throw new IllegalStateException("There are no \')\' for \'(\'");
            }
            result.add(topToken);
        }
        return result;
    }
}
