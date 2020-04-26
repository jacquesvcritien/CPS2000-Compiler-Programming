package parser.node;

import exceptions.*;
import visitor.Visitor;

/**
 * Class for if node
 */
public class ASTIf extends ASTStatement {
    //expression
    private ASTExpression expression;
    //block
    private ASTBlock block;
    //else block
    private ASTBlock elseBlock;

    /**
     * Constructor
     * @param expression expression to set
     * @param block block to set
     * @param elseBlock else block to set
     */
    public ASTIf(ASTExpression expression, ASTBlock block, ASTBlock elseBlock)
    {
        this.expression = expression;
        this.block = block;
        this.elseBlock = elseBlock;
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

    /**
     * Getter for else block
     * @return else block
     */
    public ASTBlock getElseBlock() {
        return elseBlock;
    }

    @Override
    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
