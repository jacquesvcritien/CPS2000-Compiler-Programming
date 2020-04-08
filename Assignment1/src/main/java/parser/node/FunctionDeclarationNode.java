package parser.node;

import lexer.TypeToken;

public class FunctionDeclarationNode extends Node {
    Node identifier;
    Node formalParams;
    TypeToken type;
    Node block;

    public FunctionDeclarationNode(Node identifier, Node formalParams, TypeToken type, Node block)
    {
        this.identifier = identifier;
        this.formalParams = formalParams;
        this.type = type;
        this.block = block;
    }
}
