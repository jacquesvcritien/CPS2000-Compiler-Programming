package parser.node;

import exceptions.*;
import visitor.Visitor;

import java.util.ArrayList;

public class ASTProgram implements ASTNode {
    ArrayList<ASTStatement> statements;

    public ASTProgram(ArrayList<ASTStatement> statements)
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
