package parser.node;

import lexer.Token;

public class FunctionCallNode extends Node {
    Node identifier;
    Node params;

    public FunctionCallNode(Node identifier, Node params)
    {
        this.identifier = identifier;
        this.params = params;
    }
}
