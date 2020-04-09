package parser.node;

import visitor.Visitor;

import java.util.ArrayList;

public class ASTBlock extends ASTStatement {
    ArrayList<ASTStatement> statements;

    public ASTBlock(){};
    public ASTBlock(ArrayList<ASTStatement> statements)
    {
        this.statements = statements;
    }

    public ArrayList<ASTStatement> getStatements() {
        return statements;
    }

    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

}
