package parser.node;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.UndeclaredException;
import lexer.Token;
import lexer.TypeToken;
import visitor.Visitor;

public class ASTVariableDecl extends ASTStatement {
    ASTIdentifier identifier;
    ASTExpression expression;

    public ASTVariableDecl(){};
    public ASTVariableDecl(ASTIdentifier identifier, ASTExpression expression)
    {
        this.identifier = identifier;
        this.expression = expression;
    }

    public ASTExpression getExpression() {
        return expression;
    }

    public ASTIdentifier getIdentifier() {
        return identifier;
    }

    public void accept(Visitor visitor) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException {
        visitor.visit(this);
    }
}
