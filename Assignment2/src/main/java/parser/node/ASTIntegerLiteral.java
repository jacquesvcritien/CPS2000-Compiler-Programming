package parser.node;

import lexer.Token;
import lexer.TypeToken;
import visitor.Visitor;

/**
 * Class for integer literal
 */
public class ASTIntegerLiteral extends ASTExpression {
    //value
    private int value;

    /**
     * Constructor
     * @param token token from which to set value
     */
    public ASTIntegerLiteral(Token token)
    {
        this.value = Integer.valueOf(token.getAttribute());
    }

    /**
     * Getter for value
     * @return value
     */
    public int getValue() {
        return value;
    }

    @Override
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
}
