package parser.node.statement;

import exceptions.*;
import parser.node.expression.ASTExpression;
import visitor.Visitor;

/**
 * Class for return node
 */
public class ASTReturn extends ASTStatement {
    //expression
    private ASTExpression expression;

    /**
     * Constructor
     * @param expression expression to set
     */
    public ASTReturn(ASTExpression expression)
    {
        this.expression = expression;
    }

    /**
     * Getter for expression
     * @return expression
     */
    public ASTExpression getExpression() {
        return expression;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }

}
