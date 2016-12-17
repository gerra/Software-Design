package rpn.token;

import rpn.visitors.TokenVisitor;

/**
 * Created by german on 17.12.16.
 */
public interface Token {
    void accept(TokenVisitor visitor);
}
