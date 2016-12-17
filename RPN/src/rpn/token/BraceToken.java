package rpn.token;

import rpn.visitors.TokenVisitor;

/**
 * Created by german on 17.12.16.
 */
public class BraceToken implements Token {
    public enum Type {
        LEFT, RIGHT
    }

    private Type type;

    public BraceToken(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
