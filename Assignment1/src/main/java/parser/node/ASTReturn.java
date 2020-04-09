package parser.node;

import lexer.Token;
import visitor.Visitor;

public class ASTReturn extends ASTStatement {
    ASTExpression expression;

    public ASTReturn(Token token, ASTExpression expression)
    {
        this.expression = expression;
    }

    public ASTExpression getExpression() {
        return expression;
    }

    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

}
