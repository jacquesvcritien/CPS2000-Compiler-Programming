package parser.node;

import visitor.Visitor;

public interface ASTNode {
    public void accept(Visitor visitor);
}
