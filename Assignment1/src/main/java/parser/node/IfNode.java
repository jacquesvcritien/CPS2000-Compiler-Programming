package parser.node;

public class IfNode extends Node{
    Node expression;
    Node block;
    Node elseBlock;

    public IfNode(Node expression, Node block, Node elseBlock)
    {
        this.expression = expression;
        this.block = block;
        this.elseBlock = elseBlock;
    }
}
