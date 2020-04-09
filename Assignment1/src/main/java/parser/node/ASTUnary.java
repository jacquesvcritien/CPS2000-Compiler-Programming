package parser.node;

import lexer.Token;

public class ASTUnary extends ASTExpression {
    Token unaryToken;
    ASTExpression next;

    public ASTUnary(Token unaryToken, ASTExpression next)
    {
        this.unaryToken = unaryToken;
        this.next = next;
    }
}
