package parser.node.expression;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.UndeclaredException;
import parser.node.Type;
import parser.node.expression.ASTExpression;
import visitor.Visitor;

/**
 * Class for identifier node
 */
public class ASTIdentifier extends ASTExpression {
    //name
    private String name;
    //type
    private Type type;

    /**
     * Constructor
     * @param name value to set
     */
    public ASTIdentifier(String name)  {
        this.name =name;
    }

    /**
     * Constructor with type
     * @param name value to set
     * @param type type to set
     */
    public ASTIdentifier(String name, Type type)  {

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
    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException {
        visitor.visit(this);
    }
}
