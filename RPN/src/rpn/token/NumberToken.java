package rpn.token;

import rpn.visitors.TokenVisitor;

/**
 * Created by german on 17.12.16.
 */
public class NumberToken implements Token {

    private int number;

    public NumberToken(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
