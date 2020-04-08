package parser.node;

import java.util.ArrayList;

public class FormalParamsNode extends Node {
    ArrayList<Node> expressions;

    public FormalParamsNode(ArrayList<Node> expressions)
    {
        this.expressions = expressions;
    }
}
