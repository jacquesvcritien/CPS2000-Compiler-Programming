package parser.node;

import exceptions.*;
import visitor.Visitor;

public interface ASTNode {
    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;
}
