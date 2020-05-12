package parser.node.statement;

import exceptions.*;
import parser.node.expression.ASTExpression;
import visitor.Visitor;

/**
 * Class for while node
 */
public class ASTWhile extends ASTStatement {
    //expression
    private ASTExpression expression;
    //expression
    private ASTBlock block;

    /**
     * Constructor
     * @param expression expression to set
     * @param block block to set
     */
    public ASTWhile(ASTExpression expression, ASTBlock block)
    {
        this.expression = expression;
        this.block = block;
    }

    /**
     * Getter for expression
     * @return expression
     */
    public ASTExpression getExpression() {
        return expression;
    }

    /**
     * Getter for block
     * @return block
     */
    public ASTBlock getBlock() {
        return block;
    }

    @Override
    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }

}
