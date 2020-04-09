package parser.node;

import java.util.ArrayList;

public class ASTBlock extends ASTStatement {
    ArrayList<ASTStatement> statements;

    public ASTBlock(){};
    public ASTBlock(ArrayList<ASTStatement> statements)
    {
        this.statements = statements;
    }
}
