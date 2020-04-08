package parser.node;

public class ForNode extends Node{
    Node functionDeclaration;
    Node expression;
    Node assignment;
    Node block;

    public ForNode(Node functionDeclaration, Node expression, Node assignment, Node block)
    {
        this.functionDeclaration = functionDeclaration;
        this.expression = expression;
        this.assignment = assignment;
        this.block = block;
    }
}
