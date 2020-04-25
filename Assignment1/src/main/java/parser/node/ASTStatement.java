package parser.node;

import exceptions.*;
import lexer.Token;
import visitor.Visitor;

public class ASTStatement implements ASTNode {
    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException{
        visitor.visit(this);
    }
}
