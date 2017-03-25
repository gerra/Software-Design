package rpn;

import rpn.parser.Tokenizer;
import rpn.token.Token;
import rpn.visitors.CalcVisitor;
import rpn.visitors.ParserVisitor;
import rpn.visitors.PrintVisitor;

import java.io.*;
import java.util.List;

/**
 * Created by german on 17.12.16.
 */
public class Main {
    // когда лучше передать в конструктор что-то, а когда сделать функцию, принимающую это в качестве аргумента. многопоточность и одновременное использование?
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("One argument expected");
            return;
        }
        if (args[0] == null) {
            System.err.println("Arguments should be non null");
            return;
        }
        InputStream is = new ByteArrayInputStream(args[0].getBytes());
        Tokenizer tokenizer = new Tokenizer(is);
        List<Token> infixTokens = tokenizer.extractTokens();
        ParserVisitor parserVisitor = new ParserVisitor(infixTokens);
        List<Token> rpnTokens = parserVisitor.convertToRPNTokens();
        CalcVisitor calcVisitor = new CalcVisitor(rpnTokens);
        PrintVisitor printVisitor = new PrintVisitor(rpnTokens);
        System.out.println(calcVisitor.calc());
        System.out.println(printVisitor.getOutputStream().toString());
    }
}
