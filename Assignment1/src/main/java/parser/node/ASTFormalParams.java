package parser.node;

import visitor.Visitor;

import java.util.ArrayList;

public class ASTFormalParams implements ASTNode {
    ArrayList<ASTFormalParam> expressions;

    public ASTFormalParams(ArrayList<ASTFormalParam> expressions)
    {
        this.expressions = expressions;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
