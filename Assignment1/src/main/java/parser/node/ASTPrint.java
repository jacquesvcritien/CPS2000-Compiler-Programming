package parser.node;

import exceptions.*;
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

    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
