package parser.node;

import lexer.Token;
import lexer.TypeToken;
import visitor.Visitor;

public class ASTIntegerLiteral extends ASTExpression {
    int value;

    //constructor
    public ASTIntegerLiteral(Token token)
    {
        this.value = Integer.valueOf(token.getAttribute());
    }

    public int getValue() {
        return value;
    }

    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
}
