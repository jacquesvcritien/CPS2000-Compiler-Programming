package parser.node;

public class StatementNode extends Node {
    Node token;
    Node expression;

    public StatementNode(Node token, Node expression)
    {
        this.token = token;
        this.expression = expression;
    }
}
