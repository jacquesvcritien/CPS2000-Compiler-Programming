package parser.node.expression;

import lexer.Token;
import visitor.Visitor;

/**
 * Class for float literal
 */
public class ASTFloatLiteral extends ASTExpression {
    //value
    private float value;

    /**
     * Constructor
     * @param token toke from which to set value
     */
    public ASTFloatLiteral(Token token)
    {
        this.value = Float.valueOf(token.getAttribute());
    }

    /**
     * Getter for value
     * @return value
     */
    public float getValue() {
        return value;
    }

    @Override
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
}
