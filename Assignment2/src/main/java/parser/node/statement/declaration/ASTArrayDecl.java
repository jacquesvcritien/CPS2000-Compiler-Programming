package parser.node.statement.declaration;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.ReturnTypeMismatchException;
import exceptions.UndeclaredException;
import parser.node.expression.identifier.ASTArrayIdentifier;
import parser.node.statement.ASTArrayValue;
import visitor.Visitor;

/**
 * Class for array declaration
 */
public class ASTArrayDecl extends ASTDecl {
    //values
    private ASTArrayValue values;
    //identifier
    private ASTArrayIdentifier identifier;
    /**
     * Empty constructor
     */
    public ASTArrayDecl(){};

    /**
     * Constructor
     * @param identifier identifier to set
     * @param values values to set
     */
    public ASTArrayDecl(ASTArrayIdentifier identifier, ASTArrayValue values)
    {
        this.identifier = identifier;
        this.values = values;
    }

    /**
     * Getter for identifier
     * @return identifier
     */
    public ASTArrayIdentifier getIdentifier() {
        return identifier;
    }

    /**
     * Getter for values
     * @return values
     */
    public ASTArrayValue getValues() {
        return values;
    }

    @Override
    public void accept(Visitor visitor) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
