package parser.node;

import exceptions.*;
import visitor.Visitor;

import java.util.ArrayList;

/**
 * Class for program
 */
public class ASTProgram implements ASTNode {
    //statements
    private ArrayList<ASTStatement> statements;

    /**
     * Constructor
     * @param statements statements
     */
    public ASTProgram(ArrayList<ASTStatement> statements)
    {
        this.statements = statements;
    }

    /**
     * Getter for statements
     * @return statements
     */
    public ArrayList<ASTStatement> getStatements() {
        return statements;
    }

    @Override
    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
