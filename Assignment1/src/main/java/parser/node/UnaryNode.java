package parser.node;

import lexer.Token;

public class UnaryNode extends Node {
    Token unaryToken;
    Node next;

    public UnaryNode(Token unaryToken, Node next)
    {
        this.unaryToken = unaryToken;
        this.next = next;
    }
}
