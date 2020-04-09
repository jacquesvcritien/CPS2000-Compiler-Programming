package parser.node;

import lexer.Token;
import visitor.Visitor;

public class ASTFloatLiteral extends ASTExpression {
    float value;

    //constructor
    public ASTFloatLiteral(Token token)
    {
        this.value = Float.valueOf(token.getAttribute());
    }

    public float getValue() {
        return value;
    }
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
}
