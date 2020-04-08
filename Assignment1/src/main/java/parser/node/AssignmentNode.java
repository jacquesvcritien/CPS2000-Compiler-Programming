package parser.node;

public class AssignmentNode extends Node {
    Node token;
    Node expression;

    public AssignmentNode(Node token, Node expression)
    {
        this.token = token;
        this.expression = expression;
    }
}
