package parser.node;

import exceptions.*;
import visitor.Visitor;

/**
 * Class for expression
 */
public class ASTExpression implements ASTNode{

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
