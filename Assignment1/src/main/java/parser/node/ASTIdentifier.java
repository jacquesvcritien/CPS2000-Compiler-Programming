package parser.node;

import exceptions.AlreadyDeclaredException;
import exceptions.UndeclaredException;
import visitor.Visitor;

public class ASTIdentifier extends ASTExpression {
    String value;
    String type;

    //constructor
    public ASTIdentifier(String value)  {

        this.value =value;
    }

    //constructor
    public ASTIdentifier(String value, String type)  {

        this.value =value;
        this.type = type;
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

    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException {
        visitor.visit(this);
    }
}
