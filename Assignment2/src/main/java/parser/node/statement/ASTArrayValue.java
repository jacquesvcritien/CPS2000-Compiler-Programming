package parser.node.statement;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.ReturnTypeMismatchException;
import exceptions.UndeclaredException;
import parser.node.ASTNode;
import parser.node.expression.ASTExpression;
import visitor.Visitor;

import java.util.ArrayList;

/**
 * Class for array values
 */
public class ASTArrayValue implements ASTNode {

    //list of expressions
    private ArrayList<ASTExpression> values;

    /**
     * Constructor for empty params
     */
    public ASTArrayValue() {
        this.values = new ArrayList<ASTExpression>();
    };

    /**
     * Constructor
     * @param expressions expressions to set
     */
    public ASTArrayValue(ArrayList<ASTExpression> expressions)
    {
        this.values = expressions;
    }

    /**
     * Getter for values
     * @return values
     */
    public ArrayList<ASTExpression> getValues() {
        return values;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
