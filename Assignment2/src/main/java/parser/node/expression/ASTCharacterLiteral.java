package parser.node.expression;

import lexer.Token;
import visitor.Visitor;

/**
 * Class for float literal
 */
public class ASTCharacterLiteral extends ASTExpression {
    //value
    private char value;

    /**
     * Constructor
     * @param token toke from which to set value
     */
    public ASTCharacterLiteral(Token token)
    {
        this.value = token.getAttribute().charAt(0);
    }

    @Override
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
}
