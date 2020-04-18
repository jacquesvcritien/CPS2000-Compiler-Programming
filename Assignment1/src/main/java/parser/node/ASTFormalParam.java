package parser.node;

import exceptions.AlreadyDeclaredException;
import exceptions.UndeclaredException;
import lexer.Token;
import visitor.Visitor;

public class ASTFormalParam extends ASTExpression {
    ASTIdentifier identifier;
    Token type;

    public ASTFormalParam(ASTIdentifier identifier)
    {
        this.identifier = identifier;
    }

    public ASTIdentifier getIdentifier() {
        return identifier;
    }


    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException {
        visitor.visit(this);
    }

}
