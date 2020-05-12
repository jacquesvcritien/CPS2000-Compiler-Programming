package parser.node.statement;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.UndeclaredException;
import parser.node.ASTNode;
import parser.node.expression.ASTIdentifier;
import visitor.Visitor;

/**
 * Class for formal param node
 */
public class ASTFormalParam implements ASTNode {
    //identifier
    private ASTIdentifier identifier;

    /**
     * Constructor
     * @param identifier identifier to set
     */
    public ASTFormalParam(ASTIdentifier identifier)
    {
        this.identifier = identifier;
    }

    /**
     * Getter for identifier
     * @return identifier
     */
    public ASTIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException {
        visitor.visit(this);
    }

}
