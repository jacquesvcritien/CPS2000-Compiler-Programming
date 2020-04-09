package parser.node;

import lexer.Token;

public class ASTFunctionDecl extends ASTStatement {
    ASTIdentifier identifier;
    ASTFormalParams formalParams;
    ASTBlock block;

    public ASTFunctionDecl(ASTIdentifier identifier, ASTFormalParams formalParams, ASTBlock block)
    {
        this.identifier = identifier;
        this.formalParams = formalParams;
        this.block = block;
    }
}
