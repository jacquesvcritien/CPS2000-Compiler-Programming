package parser.node;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.UndeclaredException;
import lexer.Token;
import visitor.Visitor;

public class ASTPrint extends ASTStatement {
    ASTExpression expression;

    public ASTPrint(Token token, ASTExpression expression)
    {
        this.expression = expression;
    }

    public ASTExpression getExpression() {
        return expression;
    }

    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException {
        visitor.visit(this);
    }
}
