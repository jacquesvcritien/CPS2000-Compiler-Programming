package parser.node;

import visitor.Visitor;

import java.util.ArrayList;

public class ASTActualParams implements ASTNode {
    ArrayList<ASTExpression> expressions;

    public ASTActualParams() {};
    public ASTActualParams(ArrayList<ASTExpression> expressions)
    {
        this.expressions = expressions;
    }

    public void accept(Visitor visitor) {

    }
}
