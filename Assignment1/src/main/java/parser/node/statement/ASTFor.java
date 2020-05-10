package parser.node.statement;

import exceptions.*;
import parser.node.expression.ASTExpression;
import visitor.Visitor;

/**
 * Class for For node
 */
public class ASTFor extends ASTStatement {
    //variable declaration
    private ASTVariableDecl variableDecl;
    //expression
    private ASTExpression expression;
    //assignment
    private ASTAssignment assignment;
    //block
    private ASTBlock block;

    /**
     * Constructor
     * @param variableDecl variable declaration to set
     * @param expression expression to set
     * @param assignment assignment to set
     * @param block block to set
     */
    public ASTFor(ASTVariableDecl variableDecl, ASTExpression expression, ASTAssignment assignment, ASTBlock block)
    {
        this.variableDecl = variableDecl;
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
     * Getter for variable declaration
     * @return variable declaration
     */
    public ASTVariableDecl getVariableDecl() {
        return variableDecl;
    }

    @Override
    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }

}
