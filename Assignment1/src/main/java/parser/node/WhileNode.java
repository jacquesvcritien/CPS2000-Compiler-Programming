package parser.node;

public class WhileNode extends Node{
    Node expression;
    Node block;

    public WhileNode(Node expression, Node block)
    {
        this.expression = expression;
        this.block = block;
    }
}
