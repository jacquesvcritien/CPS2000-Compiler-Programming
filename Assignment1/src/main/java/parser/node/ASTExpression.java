package parser.node;

import exceptions.*;
import lexer.TypeToken;
import visitor.Visitor;

public class ASTExpression implements ASTNode{
    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
