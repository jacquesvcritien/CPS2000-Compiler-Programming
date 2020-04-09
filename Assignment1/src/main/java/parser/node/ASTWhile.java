package parser.node;

public class ASTWhile extends ASTStatement {
    ASTExpression expression;
    ASTBlock block;

    public ASTWhile(ASTExpression expression, ASTBlock block)
    {
        this.expression = expression;
        this.block = block;
    }
}
