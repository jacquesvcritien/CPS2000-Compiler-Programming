package parser.node.expression;

import exceptions.AlreadyDeclaredException;
import exceptions.UndeclaredException;
import parser.node.Type;
import visitor.Visitor;

/**
 * Class for array identifier node
 */
public class ASTArrayIdentifier extends ASTExpression {
    //values
    private String name;
    //expression to hold size or index
    private ASTExpression sizeIndex;
    //type
    private Type type;

    /**
     * Constructor
     * @param name name to set
     */
    public ASTArrayIdentifier(String name)  {
        this.name =name;
    }

    /**
     * Constructor with type
     * @param name value to set
     * @param expression type to set
     */
    public ASTArrayIdentifier(String name, ASTExpression expression)  {

        this.name =name;
        this.sizeIndex = expression;
    }

    /**
     * Setter for type
     * @param type type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Setter for size or index
     * @param expression index to set
     */
    public void setSizeIndex(ASTExpression expression) {
        this.sizeIndex = expression;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException {
        visitor.visit(this);
    }
}
