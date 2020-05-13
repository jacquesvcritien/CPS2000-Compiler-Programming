package parser.node.expression.identifier;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.UndeclaredException;
import parser.node.Type;
import parser.node.expression.ASTExpression;
import visitor.Visitor;

/**
 * Class for identifier node
 */
public class ASTIdentifier extends ASTAbstractIdentifier {


    /**
     * Constructor
     * @param name value to set
     */
    public ASTIdentifier(String name)  {
        super(name);
    }

    /**
     * Constructor with type
     * @param name value to set
     * @param type type to set
     */
    public ASTIdentifier(String name, Type type)  {
        super(name, type);
    }


    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException {
        visitor.visit(this);
    }
}
