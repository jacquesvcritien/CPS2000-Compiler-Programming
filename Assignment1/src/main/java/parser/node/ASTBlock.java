package parser.node;

import exceptions.*;
import visitor.Visitor;

import java.util.ArrayList;

public class ASTBlock extends ASTStatement {
    ArrayList<ASTStatement> statements;

    public ASTBlock(){
        statements = new ArrayList<ASTStatement>();
    };
    public ASTBlock(ArrayList<ASTStatement> statements)
    {
        this.statements = statements;
    }

    public ArrayList<ASTStatement> getStatements() {
        return statements;
    }

    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, InvalidNodeException, ReturnTypeMismatchException {
        visitor.visit(this);
    }

}
