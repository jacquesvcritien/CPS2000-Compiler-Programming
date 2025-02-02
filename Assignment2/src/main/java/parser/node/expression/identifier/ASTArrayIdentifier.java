package parser.node.expression.identifier;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.ReturnTypeMismatchException;
import exceptions.UndeclaredException;
import parser.node.Type;
import parser.node.expression.ASTExpression;
import visitor.Visitor;

/**
 * Class for array identifier node
 */
public class ASTArrayIdentifier extends ASTAbstractIdentifier {
    //expression to hold size or index
    private ASTExpression sizeIndex;

    /**
     * Constructor
     * @param name name to set
     */
    public ASTArrayIdentifier(String name)  {
        super(name);
    }

    /**
     * Constructor with type
     * @param name value to set
     * @param expression type to set
     */
    public ASTArrayIdentifier(String name, ASTExpression expression)  {
        super(name);
        this.sizeIndex = expression;
    }

    /**
     * Setter for size or index
     * @param expression index to set
     */
    public void setSizeIndex(ASTExpression expression) {
        this.sizeIndex = expression;
    }

    /**
     * Getter for size or index
     * @return size or index
     */
    public ASTExpression getSizeIndex() {
        return sizeIndex;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException, IncorrectTypeException {
        visitor.visit(this);
    }
}
