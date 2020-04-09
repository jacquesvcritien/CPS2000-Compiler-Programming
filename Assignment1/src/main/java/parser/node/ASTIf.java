package parser.node;

public class ASTIf extends ASTStatement {
    ASTExpression expression;
    ASTBlock block;
    ASTBlock elseBlock;

    public ASTIf(ASTExpression expression, ASTBlock block, ASTBlock elseBlock)
    {
        this.expression = expression;
        this.block = block;
        this.elseBlock = elseBlock;
    }
}
