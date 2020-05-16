package parser.node.expression.identifier;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.ReturnTypeMismatchException;
import exceptions.UndeclaredException;
import parser.node.Type;
import parser.node.expression.ASTExpression;
import visitor.Visitor;

/**
 * Class for identifier node
 */
public class ASTAbstractIdentifier extends ASTExpression {
    //values
    private String name;
    //type
    private Type type;

    /**
     * Constructor
     * @param name value to set
     */
    public ASTAbstractIdentifier(String name)  {
        this.name =name;
    }

    /**
     * Constructor with type
     * @param name value to set
     * @param type type to set
     */
    public ASTAbstractIdentifier(String name, Type type)  {

        this.name =name;
        this.type = type;
    }

    /**
     * Setter for type
     * @param type type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Getter for value
     * @return value
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for type
     * @return type
     */
    public Type getType() {
        return type;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
