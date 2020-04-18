package parser.node;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.UndeclaredException;
import lexer.TypeToken;
import visitor.Visitor;

public class ASTExpression implements ASTNode{
    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException {
        visitor.visit(this);
    }
}
