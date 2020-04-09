package parser.node;

import lexer.TypeToken;
import visitor.Visitor;

public class ASTExpression implements ASTNode{
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
}
