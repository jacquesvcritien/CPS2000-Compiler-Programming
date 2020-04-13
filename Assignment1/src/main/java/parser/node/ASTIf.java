package parser.node;

import visitor.Visitor;

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

    public ASTExpression getExpression() {
        return expression;
    }

    public ASTBlock getBlock() {
        return block;
    }

    public ASTBlock getElseBlock() {
        return elseBlock;
    }

    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
}