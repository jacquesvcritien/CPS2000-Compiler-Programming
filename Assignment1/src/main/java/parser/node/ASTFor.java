package parser.node;

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
}
