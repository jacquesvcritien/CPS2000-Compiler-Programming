package parser.node;

import exceptions.*;
import visitor.Visitor;

public class ASTWhile extends ASTStatement {
    ASTExpression expression;
    ASTBlock block;

    public ASTWhile(ASTExpression expression, ASTBlock block)
    {
        this.expression = expression;
        this.block = block;
    }

    public ASTExpression getExpression() {
        return expression;
    }

    public ASTBlock getBlock() {
        return block;
    }

    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, InvalidNodeException, ReturnTypeMismatchException {
        visitor.visit(this);
    }

}
