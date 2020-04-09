package parser.node;

import lexer.Token;

public class ASTFormalParam extends ASTExpression {
    ASTIdentifier identifier;
    Token type;

    public ASTFormalParam(ASTIdentifier identifier, Token type)
    {
        this.identifier = identifier;
        this.type = type;
    }
}
