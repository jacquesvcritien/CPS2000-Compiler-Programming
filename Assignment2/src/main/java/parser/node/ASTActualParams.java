package parser.node;

import exceptions.*;
import visitor.Visitor;

import java.util.ArrayList;

/**
 * Class for actual params
 */
public class ASTActualParams implements ASTNode {

    //list of expressions
    private ArrayList<ASTExpression> expressions;

    /**
     * Constructor for empty params
     */
    public ASTActualParams() {
        this.expressions = new ArrayList<ASTExpression>();
    };

    /**
     * Constructor
     * @param expressions expressions to set
     */
    public ASTActualParams(ArrayList<ASTExpression> expressions)
    {
        this.expressions = expressions;
    }

    /**
     * Getter for expressions
     * @return expressions
     */
    public ArrayList<ASTExpression> getExpressions() {
        return expressions;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
