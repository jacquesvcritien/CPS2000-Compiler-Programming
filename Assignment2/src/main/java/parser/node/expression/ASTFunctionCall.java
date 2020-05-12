package parser.node.expression;

import exceptions.*;
import visitor.Visitor;

/**
 * Class for function call node
 */
public class ASTFunctionCall extends ASTExpression {
    //identifier
    private ASTIdentifier identifier;
    //params
    private ASTActualParams params;

    /**
     * Constructor
     * @param identifier identifier to set
     * @param params params to set
     */
    public ASTFunctionCall(ASTIdentifier identifier, ASTActualParams params)
    {
        this.identifier = identifier;
        this.params = params;
    }

    /**
     * Getter for identifier
     * @return identifier
     */
    public ASTIdentifier getIdentifier() {
        return identifier;
    }

    /**
     * Getter for params
     * @return params
     */
    public ASTActualParams getParams() {
        return params;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
