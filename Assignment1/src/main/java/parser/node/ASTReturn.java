package parser.node;

import lexer.Token;

public class ASTReturn extends ASTStatement {
    ASTExpression expression;

    public ASTReturn(Token token, ASTExpression expression)
    {
        this.expression = expression;
    }

    public ASTExpression getExpression() {
        return expression;
    }
}
