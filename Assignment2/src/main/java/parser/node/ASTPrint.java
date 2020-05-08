package parser.node;

import exceptions.*;
import lexer.Token;
import visitor.Visitor;

/**
 * Class for print node
 */
public class ASTPrint extends ASTStatement {
    //expression
    private ASTExpression expression;

    /**
     * Constructor for print
     * @param expression
     */
    public ASTPrint(ASTExpression expression)
    {
        this.expression = expression;
    }

    /**
     * Getter for expression
     * @return
     */
    public ASTExpression getExpression() {
        return expression;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
