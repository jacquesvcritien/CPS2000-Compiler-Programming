package parser.node;

import exceptions.*;
import visitor.Visitor;

/**
 * Class for For node
 */
public class ASTFor extends ASTStatement {
    //declaration
    private ASTDecl declaration;
    //expression
    private  ASTExpression expression;
    //assignment
    private ASTAssignment assignment;
    //block
    private ASTBlock block;

    /**
     * Constructor
     * @param declaration variable declaration to set
     * @param expression expression to set
     * @param assignment assignment to set
     * @param block block to set
     */
    public ASTFor(ASTDecl declaration, ASTExpression expression, ASTAssignment assignment, ASTBlock block)
    {
        this.declaration = declaration;
        this.expression = expression;
        this.assignment = assignment;
        this.block = block;
    }

    /**
     * Getter for block
     * @return block
     */
    public ASTBlock getBlock() {
        return block;
    }

    /**
     * Getter for expression
     * @return expression
     */
    public ASTExpression getExpression() {
        return expression;
    }

    /**
     * Getter for assignment
     * @return assignment
     */
    public ASTAssignment getAssignment() {
        return assignment;
    }

    /**
     * Getter for declaration
     * @return declaration
     */
    public ASTDecl getDeclaration() {
        return declaration;
    }

    @Override
    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }

}
