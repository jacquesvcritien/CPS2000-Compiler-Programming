package parser.node;

import exceptions.InvalidNodeException;
import lexer.Token;
import lexer.TypeToken;
import visitor.Visitor;

public class ASTIdentifier extends ASTExpression {
    String value;
    String type;

    //constructor
    public ASTIdentifier(String value) throws InvalidNodeException {

        this.value =value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
}
