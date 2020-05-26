package parser.node.expression;

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
    //operator
    private String operator;

    /**
     * Constructor
     * @param left left expression
     * @param operator operator to perform on expressions
     * @param right right expression
     */
    public ASTBinExpression(ASTExpression left, String operator, ASTExpression right)
    {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    /**
     * Getter for operator
     * @return operator
     */
    public String getOperator() {
        return operator;
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
