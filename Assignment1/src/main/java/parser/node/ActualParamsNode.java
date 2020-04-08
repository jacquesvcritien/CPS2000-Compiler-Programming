package parser.node;

import java.util.ArrayList;

public class ActualParamsNode extends Node {
    ArrayList<Node> expressions;

    public ActualParamsNode(ArrayList<Node> expressions)
    {
        this.expressions = expressions;
    }
}
