package parser.node;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.InvalidNodeException;
import exceptions.UndeclaredException;
import lexer.Token;
import visitor.Visitor;

public class ASTReturn extends ASTStatement {
    ASTExpression expression;

    public ASTReturn(Token token, ASTExpression expression)
    {
        this.expression = expression;
    }

    public ASTExpression getExpression() {
        return expression;
    }

    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, InvalidNodeException, UndeclaredException {
        visitor.visit(this);
    }

}
