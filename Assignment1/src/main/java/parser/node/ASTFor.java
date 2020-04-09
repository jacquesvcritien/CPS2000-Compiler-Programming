package parser.node;

import visitor.Visitor;

public class ASTFor extends ASTStatement {
    ASTVariableDecl variableDecl;
    ASTExpression expression;
    ASTAssignment assignment;
    ASTBlock block;

    public ASTFor(ASTVariableDecl variableDecl, ASTExpression expression, ASTAssignment assignment, ASTBlock block)
    {
        this.variableDecl = variableDecl;
        this.expression = expression;
        this.assignment = assignment;
        this.block = block;
    }

    public ASTBlock getBlock() {
        return block;
    }

    public ASTExpression getExpression() {
        return expression;
    }

    public ASTAssignment getAssignment() {
        return assignment;
    }

    public ASTVariableDecl getVariableDecl() {
        return variableDecl;
    }

    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

}
