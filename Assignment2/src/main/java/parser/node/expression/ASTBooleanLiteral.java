package parser.node;

import lexer.Token;
import visitor.Visitor;

/**
 * Class for boolean literal
 */
public class ASTBooleanLiteral extends ASTExpression {
    //value
    private boolean value;

    /**
     * Constructor
     * @param token token from which to take value
     */
    public ASTBooleanLiteral(Token token)
    {
        this.value = Boolean.valueOf(token.getAttribute());
    }

    /**
     * Getter for value
     * @return value
     */
    public boolean getValue() {
        return value;
    }

    @Override
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

}
