package parser.node;

import lexer.TypeToken;

public class VariableDeclarationNode extends Node {
    Node identifier;
    TypeToken type;
    Node expression;

    public VariableDeclarationNode(Node identifier, TypeToken type, Node expression)
    {
        this.identifier = identifier;
        this.type = type;
        this.expression = expression;
    }
}
