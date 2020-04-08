package parser.node;

import java.util.ArrayList;

public class ProgramNode extends Node {
    ArrayList<Node> statements;

    public ProgramNode(ArrayList<Node> statements)
    {
        this.statements = statements;
    }
}
