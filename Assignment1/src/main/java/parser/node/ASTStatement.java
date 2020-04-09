package parser.node;

import lexer.Token;
import visitor.Visitor;

public class ASTStatement implements ASTNode {
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
}
