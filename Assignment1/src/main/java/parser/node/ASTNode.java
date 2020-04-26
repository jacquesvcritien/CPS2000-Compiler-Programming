package parser.node;

import exceptions.*;
import visitor.Visitor;

/**
 * Interface for node
 */
public interface ASTNode {
    /**
     * Accept method to visit for visitor class
     * @param visitor visitor to visit
     * @throws IncorrectTypeException
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws ReturnTypeMismatchException
     */
    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;
}
