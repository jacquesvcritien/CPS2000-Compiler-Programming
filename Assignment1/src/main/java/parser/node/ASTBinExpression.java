package parser.node;

import exceptions.*;
import visitor.Visitor;

public class ASTBinExpression extends ASTExpression {
    ASTExpression left;
    ASTExpression right;
//    ASTExpression head;
    String operand;

    //constructor
    public ASTBinExpression(ASTExpression left, String operand, ASTExpression right)
    {
        this.left = left;
        this.operand = operand;
        this.right = right;
    }

    public String getOperand() {
        return operand;
    }

    public ASTExpression getLeft() {
        return left;
    }

    public ASTExpression getRight() {
        return right;
    }

    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
