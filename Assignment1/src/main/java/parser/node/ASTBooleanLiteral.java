package parser.node;

import lexer.Token;
import visitor.Visitor;

public class ASTBooleanLiteral extends ASTExpression {
    boolean value;

    //constructor
    public ASTBooleanLiteral(Token token)
    {
        this.value = Boolean.valueOf(token.getAttribute());
    }

    public boolean getValue() {
        return value;
    }

    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }


}
