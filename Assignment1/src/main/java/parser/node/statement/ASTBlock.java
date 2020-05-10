package parser.node.statement;

import exceptions.*;
import visitor.Visitor;

import java.util.ArrayList;

/**
 * Class for node block
 */
public class ASTBlock extends ASTStatement {
    //statements
    private ArrayList<ASTStatement> statements;

    /**
     * Empty constructor
     */
    public ASTBlock(){
        statements = new ArrayList<ASTStatement>();
    };

    /**
     * Constructor
     * @param statements statements to set
     */
    public ASTBlock(ArrayList<ASTStatement> statements)
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
