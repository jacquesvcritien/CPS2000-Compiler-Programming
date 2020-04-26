package parser.node;

import exceptions.AlreadyDeclaredException;
import exceptions.UndeclaredException;
import visitor.Visitor;

/**
 * Class for identifier node
 */
public class ASTIdentifier extends ASTExpression {
    //values
    private String value;
    //type
    private String type;

    /**
     * Constructor
     * @param value value to set
     */
    public ASTIdentifier(String value)  {
        this.value =value;
    }

    /**
     * Constructor with type
     * @param value value to set
     * @param type type to set
     */
    public ASTIdentifier(String value, String type)  {

        this.value =value;
        this.type = type;
    }

    /**
     * Setter for type
     * @param type type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for value
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter for type
     * @return type
     */
    public String getType() {
        return type;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException {
        visitor.visit(this);
    }
}
