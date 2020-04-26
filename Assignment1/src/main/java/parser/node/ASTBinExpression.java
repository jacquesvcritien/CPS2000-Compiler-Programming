package parser.node;

import exceptions.*;
import visitor.Visitor;

/**
 * Class for binary expression node
 */
public class ASTBinExpression extends ASTExpression {
    //left expression
    private ASTExpression left;
    //right expression
    private ASTExpression right;
    //operand
    private String operand;

    /**
     * Constructor
     * @param left left expression
     * @param operand operand to perform on expressions
     * @param right right expression
     */
    public ASTBinExpression(ASTExpression left, String operand, ASTExpression right)
    {
        this.left = left;
        this.operand = operand;
        this.right = right;
    }

    /**
     * Getter for operand
     * @return operand
     */
    public String getOperand() {
        return operand;
    }

    /**
     * Getter for left expression
     * @return left expression
     */
    public ASTExpression getLeft() {
        return left;
    }

    /**
     * Getter for right expression
     * @return right expression
     */
    public ASTExpression getRight() {
        return right;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
