package parser.node;

import lexer.Token;
import visitor.Visitor;

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

    public ASTBlock getBlock() {
        return block;
    }

    public ASTFormalParams getFormalParams() {
        return formalParams;
    }

    public ASTIdentifier getIdentifier() {
        return identifier;
    }

    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

}
