package parser.node;

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


    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

}
